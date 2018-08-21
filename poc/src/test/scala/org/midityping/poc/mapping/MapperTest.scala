package poc.src.test.scala.org.midityping.poc.mapping

import org.midityping.poc.actions.{ActionDescriptor => ActionD}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Strike, EventDescriptor => EventD, StrikeDescriptor => StrikeD}
import org.midityping.poc.mapping.storage.CustomMappingLoader
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class MapperTest extends SpecificationWithJUnit with TestSupport {
  val mapC = Mapping(StrikeD("C4"), ActionD("C"))
  val mapD = Mapping(StrikeD("D4"), ActionD("D"))
  val mapC4andF4 = Mapping((StrikeD(Seq(EventD("C4"), EventD("F4"))), ActionD("X")))

  "Mapper" >> {
    "map NoteOn event to KeyStroke action" in {
      val mapper = Mapper.withMapping(mapC)
      mapper.getActionDescriptor(Strike(anEvent(Note.C4))) === Some(ActionD("C"))
    }

    "map NoteOn event to KeyStroke action using a mapping file" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/mapping.mdt"))
      mapper.getActionDescriptor(Strike(anEvent(Note.C4))) === Some(ActionD("C"))
    }

    "map NoteOn event to KeyStroke action using a mapping, event type omitted" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/mapping.mdt"))
      mapper.getActionDescriptor(Strike(anEvent(Note.D4))) === Some(ActionD("D"))
    }

    "map NoteOn event to KeyStroke action using a mapping, action type omitted" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/mapping.mdt"))
      mapper.getActionDescriptor(Strike(anEvent(Note.E4))) === Some(ActionD("E"))
    }

    "map NoteOn event to KeyStroke action using a mapping, event type and action type omitted" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/mapping.mdt"))
      mapper.getActionDescriptor(Strike(anEvent(Note.F4))) === Some(ActionD("F"))
    }

    "use multiple mappings" in {
      val mapper = new Mapper
      mapper.appendMapping(mapC)
      mapper.appendMapping(mapD)
      mapper.getActionDescriptor(Strike(anEvent(Note.C4))) === Some(ActionD("C"))
      mapper.getActionDescriptor(Strike(anEvent(Note.D4))) === Some(ActionD("D"))
    }

    "map strike with two events" in {
      val mapper = Mapper.withMapping(mapC4andF4)
      mapper.getActionDescriptor(Strike(Seq(anEvent(Note.C4), anEvent(Note.F4)))) === Some(ActionD("X"))
    }

    "map strike with two events using mapping file" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/map-c4-and-f4.mdt"))
      mapper.getActionDescriptor(Strike(Seq(anEvent(Note.C4), anEvent(Note.F4)))) === Some(ActionD("X"))
    }
  }
}
