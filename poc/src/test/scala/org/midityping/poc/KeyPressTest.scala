package poc.src.test.scala.org.midityping.poc

import javax.sound.midi.ShortMessage
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
      listener.message(new ShortMessage(ShortMessage.NOTE_ON, 1, 85, 127))
      actionExecutor.lastAction === Some(KeyPressAction("a"))
    }
  }
}
