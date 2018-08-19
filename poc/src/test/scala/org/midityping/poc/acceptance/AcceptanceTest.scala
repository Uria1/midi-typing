package poc.src.test.scala.org.midityping.poc.acceptance

import org.midityping.poc.actions.{DefaultActionFactory, KeyPressAction}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Event, EventType}
import org.midityping.poc.mapping.Mapper
import org.midityping.poc.midi.MidiEventListener
import org.midityping.poc.system.{DefaultEventHandler, MidiTypingSystem}
import org.specs2.matcher.Scope
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class AcceptanceTest extends SpecificationWithJUnit with TestSupport {

  trait Context extends Scope {
    val mapper = new Mapper
    val eventListener = new MidiEventListener
    val actionFactory = new DefaultActionFactory
    val actionExecutor = new ActionExecutorStub
    val eventHandler = new DefaultEventHandler(mapper, actionFactory, actionExecutor)
    val system = new MidiTypingSystem(eventListener, eventHandler, mapper, actionFactory, actionExecutor)
  }

  "MidiTypingSystem" should {
    "trigger a key press as a response for a note-on event" in new Context {
      system.loadMappingResource("/mapping.json")
      eventListener.triggerEvent(Event(EventType.MidiNoteOn, 0, 1, 127, Note.C4))
      actionExecutor.lastAction === Some(KeyPressAction("c"))
    }

    "trigger a key press as a response for multiple events" in new Context {
      system.loadMappingResource("/mapping.json")

      eventListener.triggerEvent(anEvent(timestamp = 0, note = Note.C4))
      Thread.sleep(10)
      eventListener.triggerEvent(anEvent(timestamp = 10, note = Note.F4))

      actionExecutor.lastAction === Some(KeyPressAction("F1"))
    }
  }
}
