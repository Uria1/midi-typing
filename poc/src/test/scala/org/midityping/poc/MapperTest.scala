package poc.src.test.scala.org.midityping.poc

import org.midityping.poc.actions.{ActionDescriptor, ActionType}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Event, EventDescriptor, EventType}
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.midityping.poc.system.JsonMappingLoader
import org.specs2.mutable.SpecificationWithJUnit

class MapperTest extends SpecificationWithJUnit with TestSupport {
  "Mapper" should {
    "map NoteOn event to KeyPress action" in {
      val m = Mapping(Map(
        EventDescriptor(EventType.MidiNoteOn, "C4").asKey -> ActionDescriptor(ActionType.KeyPress, "a")
      ))
      val mapper = new Mapper(m)
      mapper.getActionDescriptorFor(Event(EventType.MidiNoteOn, defaultMidiChannel, randomVelocity, Note.C4)) === Some(ActionDescriptor(ActionType.KeyPress, "a"))
    }
    "map NoteOn event to KeyPress action using mapping file" in {
      val mapper = new Mapper(JsonMappingLoader.load("/mapping.json"))
      mapper.getActionDescriptorFor(Event(EventType.MidiNoteOn, defaultMidiChannel, randomVelocity, Note.C4)) === Some(ActionDescriptor(ActionType.KeyPress, "c"))
    }
  }
}
