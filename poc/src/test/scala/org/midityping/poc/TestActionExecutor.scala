package poc.src.test.scala.org.midityping.poc

import org.midityping.poc.{Action, ActionExecutor}

class TestActionExecutor extends ActionExecutor {
  var lastAction: Option[Action] = None

  override def execute(action: Action): Unit = {
    lastAction = Some(action)
  }
}
