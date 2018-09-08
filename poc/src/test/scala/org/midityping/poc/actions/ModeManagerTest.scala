package poc.src.test.scala.org.midityping.poc.actions

import org.midityping.poc.actions.ModeManager
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.testsupport.TestSupport

class ModeManagerTest extends SpecificationWithJUnit with TestSupport {
  "ModeManager" should {
    "invoke listener on mode change" in {
      val mm = new ModeManager
      var listenerMode = mm.currentMode
      val newMode = randomStr
      mm.subscribe((mode: String) => {
        listenerMode = mode
      })
      mm.currentMode = newMode
      listenerMode === newMode
    }
  }
}
