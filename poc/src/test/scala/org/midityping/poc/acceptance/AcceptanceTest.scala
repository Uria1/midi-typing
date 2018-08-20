package poc.src.test.scala.org.midityping.poc.acceptance

import org.midityping.poc.actions.KeyPressAction
import org.midityping.poc.common.Note
import org.midityping.poc.events._
import org.midityping.poc.system.MidiTypingSystem
import org.specs2.matcher.Scope
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class AcceptanceTest extends SpecificationWithJUnit with TestSupport {

  trait Context extends Scope {
    val actionExecutorStub = new ActionExecutorStub
    val system = new MidiTypingSystem(actionExecutorStub)
    system.loadMappingResource("/acceptance-test-mapping.mdt")
  }

  "MidiTypingSystem" should {
    "trigger a key press as a response for a note-on event" in new Context {
      system.eventListener.triggerEvent(Event(EventType.MidiNoteOn, 0, 1, 127, Note.C4))
      eventually(actionExecutorStub.lastAction === Some(KeyPressAction("C")))
    }

    "trigger a key press as a response for multiple events" in new Context {
      system.eventListener.triggerEvent(anEvent(timestamp = 0, note = Note.C4))
      Thread.sleep(10)
      system.eventListener.triggerEvent(anEvent(timestamp = 10, note = Note.F4))

      eventually(actionExecutorStub.lastAction === Some(KeyPressAction("F1")))
    }
  }
}
