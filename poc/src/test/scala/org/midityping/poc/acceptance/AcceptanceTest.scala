package poc.src.test.scala.org.midityping.poc.acceptance

import org.midityping.poc.actions._
import org.midityping.poc.common.{Mode, Note}
import org.midityping.poc.events._
import org.midityping.poc.logging.aLogger
import org.midityping.poc.system.MidiTypingSystem
import org.midityping.poc.system.events.{SystemEvent, SystemEventType}
import org.specs2.matcher.Scope
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class AcceptanceTest extends SpecificationWithJUnit with TestSupport {
  val logger = aLogger.forClass(getClass)

  trait Context extends Scope {
    val actionExecutorStub = new ActionExecutorStub
    val system = new MidiTypingSystem(actionExecutorStub)
    system.loadMappingResource("/acceptance/default.mdt")
    system.loadMappingResource("/acceptance/numbers.mdt")
    system.loadMappingResource("/acceptance/actions.mdt")

    val systemEventListener = new SystemEventListenerStub
    system subscribe systemEventListener

    def triggerEvents(events: Event*) = {
      events.foldLeft(0L)((lastTimestamp, event) => {
        logger.info(s"sleeping ${event.timestamp - lastTimestamp}")
        Thread.sleep(event.timestamp - lastTimestamp)
        system.eventListener.triggerEvent(event)
        event.timestamp
      })
    }
  }

  "MidiTypingSystem" should {
    "trigger a key stroke as a response for a note-on event" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.C4))
      eventually(actionExecutorStub.lastAction === Some(KeyStrokeAction("C")))
    }

    "trigger a key stroke as a response for multiple events" in new Context {
      triggerEvents(
        anEvent(timestamp = 0, note = Note.C4),
        anEvent(timestamp = 10, note = Note.F4)
      )

      eventually(actionExecutorStub.lastAction === Some(KeyStrokeAction("F1")))
    }

    "trigger a key stroke as a response for multiple events when notes are reversed" in new Context {
      triggerEvents(
        anEvent(timestamp = 0, note = Note.F4),
        anEvent(timestamp = 10, note = Note.C4)
      )

      eventually(actionExecutorStub.lastAction === Some(KeyStrokeAction("F1")))
    }

    "trigger a key stroke with modifier key" in new Context {
      triggerEvents(
        anEvent(timestamp = 0, note = Note.D4),
        anEvent(timestamp = 10, note = Note.G4),
        anEvent(timestamp = 210, note = Note.C4),
        anEvent(timestamp = 400, note = Note.Ds4),
        anEvent(timestamp = 410, note = Note.Gs4)
      )

      eventually {
        actionExecutorStub.actions must haveSize(3)
        actionExecutorStub.actions.head === KeyPressAction("CONTROL")
        actionExecutorStub.actions(1) === KeyStrokeAction("C")
        actionExecutorStub.actions(2) === KeyReleaseAction("CONTROL")
      }
    }

    "trigger key strokes in different modes" in new Context {
      system.currentMode === Mode.default
      triggerEvents(anEvent(timestamp = 0, note = Note.C4))
      eventually(actionExecutorStub.lastAction === Some(KeyStrokeAction("C")))

      triggerEvents(anEvent(timestamp = 100, note = Note.C2))
      eventually(system.currentMode === "numbers")

      triggerEvents(anEvent(timestamp = 200, note = Note.C4))
      eventually(actionExecutorStub.lastAction === Some(KeyStrokeAction("1")))
    }

    "trigger ModeChange system event" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.C2))
      eventually(systemEventListener.lastEvent(SystemEventType.ModeChange) ===
        Some(SystemEvent(SystemEventType.ModeChange, "numbers")))
    }

    "trigger NoteStrike system event" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.C4))
      eventually(systemEventListener.lastEvent === Some(SystemEvent(SystemEventType.NoteStrike, "C4")))
    }

    "trigger NoteStrike system event with two notes" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.C4))
      triggerEvents(anEvent(timestamp = 30, note = Note.F4))
      eventually(systemEventListener.lastEvent === Some(SystemEvent(SystemEventType.NoteStrike, "C4,F4")))
    }

    "trigger Action system event" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.C4))
      eventually(systemEventListener.lastEvent(SystemEventType.Action) ===
        Some(SystemEvent(SystemEventType.Action, "KeyStroke:C")))
    }

    "trigger UnmappedStrike system event" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.Fs4))
      eventually(systemEventListener.lastEvent(SystemEventType.UnmappedStrike) ===
        Some(SystemEvent(SystemEventType.UnmappedStrike, "F#4")))
    }

    "use mode change actions in '*' mode to change the mode while in any other mode" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.C2))
      eventually(system.currentMode === "numbers")
      triggerEvents(anEvent(timestamp = 100, note = Note.D2))
      eventually(system.currentMode === "default")
    }

    "trigger a key stroke with mapped SHIFT modifier" in new Context {
      triggerEvents(anEvent(timestamp = 0, note = Note.A4))
      eventually(actionExecutorStub.actions.head === KeyStrokeAction("4", Modifiers.Shift))
    }
  }
}
