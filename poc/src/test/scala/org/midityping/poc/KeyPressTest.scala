package poc.src.test.scala.org.midityping.poc

import org.midityping.poc._
import org.midityping.poc.midi.MidiEventListener
import org.specs2.mutable.SpecificationWithJUnit

class KeyPressTest extends SpecificationWithJUnit {
  "a" should {
    "a note on should trigger key press" in {
      val mappingLoader = new JsonMappingLoader
      val mapper = new Mapper(mappingLoader.load("/mapping.json"))
      val listener = new MidiEventListener
      val actionFactory = new DefaultActionFactory
      val actionExecutor = new TestActionExecutor
      val handler = new DefaultEventHandler(mapper, actionFactory, actionExecutor)
      listener.subscribe(handler)
      listener.sendEventToHandlers(Event(EventType.MidiNoteOn, 1, 127, Note.C4))
      actionExecutor.lastAction === Some(KeyPressAction("a"))
    }
  }
}
