package poc.src.test.scala.org.midityping.poc.acceptance

import org.midityping.poc.actions.{Action, ActionExecutor}

class ActionExecutorStub extends ActionExecutor {
  var lastAction: Option[Action] = None

  override def execute(action: Action): Unit = {
    lastAction = Some(action)
  }
}
