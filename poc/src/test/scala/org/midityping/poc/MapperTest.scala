package poc.src.test.scala.org.midityping.poc

import org.midityping.poc.actions.{ActionDescriptor, ActionType}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Event, EventDescriptor, EventType}
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.midityping.poc.system.JsonMappingLoader
import org.specs2.mutable.SpecificationWithJUnit

class MapperTest extends SpecificationWithJUnit {
  "Mapper" should {
    "map NoteOn event to KeyPress action" in {
      val m = Mapping(Map(
        EventDescriptor(EventType.MidiNoteOn, "60").asKey -> ActionDescriptor(ActionType.KeyPress, "a")
      ))
      val mapper = new Mapper(m)
      mapper.getActionDescriptorFor(Event(EventType.MidiNoteOn, 1, 127, Note.C4)) === Some(ActionDescriptor(ActionType.KeyPress, "a"))
    }
    "map NoteOn event to KeyPress action using mapping file" in {
      val mappingLoader = new JsonMappingLoader
      val mapper = new Mapper(mappingLoader.load("/mapping.json"))
      mapper.getActionDescriptorFor(Event(EventType.MidiNoteOn, 1, 127, Note.C4)) === Some(ActionDescriptor(ActionType.KeyPress, "a"))
    }
  }
}
