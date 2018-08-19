package poc.src.test.scala.org.midityping.poc.mapping

import org.midityping.poc.actions.{ActionDescriptor, ActionType}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{EventDescriptor, EventType, Strike}
import org.midityping.poc.mapping.storage.JsonMappingLoader
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class MapperTest extends SpecificationWithJUnit with TestSupport {
  val mapC = Mapping(Map(
    EventDescriptor(EventType.MidiNoteOn, "C4").asKey -> ActionDescriptor(ActionType.KeyPress, "c")
  ))
  val mapD = Mapping(Map(
    EventDescriptor(EventType.MidiNoteOn, "D4").asKey -> ActionDescriptor(ActionType.KeyPress, "d")
  ))

  "Mapper" should {
    "map NoteOn event to KeyPress action" in {
      val mapper = Mapper.withMapping(mapC)
      mapper.getActionDescriptorFor(Strike(anEvent(Note.C4))) === Some(ActionDescriptor(ActionType.KeyPress, "c"))
    }
    "map NoteOn event to KeyPress action using mapping file" in {
      val mapper = Mapper.withMapping(JsonMappingLoader.load("/mapping.json"))
      mapper.getActionDescriptorFor(Strike(anEvent(Note.C4))) === Some(ActionDescriptor(ActionType.KeyPress, "c"))
    }
    "use multiple mappings" in {
      val mapper = new Mapper
      mapper.appendMapping(mapC)
      mapper.appendMapping(mapD)
      mapper.getActionDescriptorFor(Strike(anEvent(Note.C4))) === Some(ActionDescriptor(ActionType.KeyPress, "c"))
      mapper.getActionDescriptorFor(Strike(anEvent(Note.D4))) === Some(ActionDescriptor(ActionType.KeyPress, "d"))
    }
  }
}
