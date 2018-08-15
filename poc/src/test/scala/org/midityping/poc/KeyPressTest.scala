package poc.src.test.scala.org.midityping.poc

import org.midityping.poc.actions.{DefaultActionFactory, KeyPressAction}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Event, EventType}
import org.midityping.poc.mapping.Mapper
import org.midityping.poc.midi.MidiEventListener
import org.midityping.poc.system.{DefaultEventHandler, MidiTypingSystem}
import org.specs2.mutable.SpecificationWithJUnit

class KeyPressTest extends SpecificationWithJUnit {
  "a" should {
    "a note on should trigger key press" in {
      val mapper = new Mapper
      val eventListener = new MidiEventListener
      val actionFactory = new DefaultActionFactory
      val actionExecutor = new TestActionExecutor
      val eventHandler = new DefaultEventHandler(mapper, actionFactory, actionExecutor)
      val system = new MidiTypingSystem(eventListener, eventHandler, mapper, actionFactory, actionExecutor)
      system.loadMappingResource("/mapping.json")
      eventListener.sendEventToHandlers(Event(EventType.MidiNoteOn, 1, 127, Note.C4))
      actionExecutor.lastAction === Some(KeyPressAction("c"))
    }
  }
}
