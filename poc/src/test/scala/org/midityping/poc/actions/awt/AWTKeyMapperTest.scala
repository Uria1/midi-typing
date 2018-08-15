package poc.src.test.scala.org.midityping.poc.actions.awt

import java.awt.event.KeyEvent

import org.midityping.poc.actions.awt.AWTKeyMapper
import org.specs2.mutable.SpecificationWithJUnit

class AWTKeyMapperTest extends SpecificationWithJUnit {
  "AWTKeyMapper" should {
    "map the A key" in {
      AWTKeyMapper.mapKey("A") === KeyEvent.VK_A
    }
  }
}
