package poc.src.test.scala.org.midityping.poc

import org.midityping.poc._
import org.midityping.poc.common.Note
import org.specs2.mutable.SpecificationWithJUnit

class NoteTest extends SpecificationWithJUnit {
  "Note" should {
    "be created as A0 (lowest)" in {
      val note = Note(21)
      note === Note("A", 0, 21)
    }
    "be created as C4" in {
      val note = Note(60)
      note === Note("C", 4, 60)
    }
    "be created as G9 (highest)" in {
      val note = Note(127)
      note === Note("G", 9, 127)
    }
  }
}
