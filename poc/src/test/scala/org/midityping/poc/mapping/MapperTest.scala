package poc.src.test.scala.org.midityping.poc.mapping

import org.midityping.poc.actions.{ActionDescriptor => ActionD}
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Strike, EventDescriptor => EventD, StrikeDescriptor => StrikeD}
import org.midityping.poc.mapping.storage.CustomMappingLoader
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class MapperTest extends SpecificationWithJUnit with TestSupport {
  val mapC = Mapping(StrikeD("C4"), ActionD("c"))
  val mapD = Mapping(StrikeD("D4"), ActionD("d"))
  val mapC4andF4 = Mapping((StrikeD(Seq(EventD("C4"), EventD("F4"))), ActionD("x")))

  "Mapper" >> {
    "map NoteOn event to KeyPress action" in {
      val mapper = Mapper.withMapping(mapC)
      mapper.getActionDescriptor(Strike(anEvent(Note.C4))) === Some(ActionD("c"))
    }

    "map NoteOn event to KeyPress action using a mapping file" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/mapping.mdt"))
      mapper.getActionDescriptor(Strike(anEvent(Note.C4))) === Some(ActionD("c"))
    }

    "use multiple mappings" in {
      val mapper = new Mapper
      mapper.appendMapping(mapC)
      mapper.appendMapping(mapD)
      mapper.getActionDescriptor(Strike(anEvent(Note.C4))) === Some(ActionD("c"))
      mapper.getActionDescriptor(Strike(anEvent(Note.D4))) === Some(ActionD("d"))
    }

    "map strike with two events" in {
      val mapper = Mapper.withMapping(mapC4andF4)
      mapper.getActionDescriptor(Strike(Seq(anEvent(Note.C4), anEvent(Note.F4)))) === Some(ActionD("x"))
    }

    "map strike with two events using mapping file" in {
      val mapper = Mapper.withMapping(CustomMappingLoader.load("/mapC4andF4.mdt"))
      mapper.getActionDescriptor(Strike(Seq(anEvent(Note.C4), anEvent(Note.F4)))) === Some(ActionD("x"))
    }
  }
}
