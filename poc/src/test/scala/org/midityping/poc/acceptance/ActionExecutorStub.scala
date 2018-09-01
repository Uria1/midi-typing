package poc.src.test.scala.org.midityping.poc.acceptance

import org.midityping.poc.actions.{Action, ActionExecutor, ChangeModeAction}

class ActionExecutorStub extends ActionExecutor {
  var lastAction: Option[Action] = None
  var actions = Seq.empty[Action]

  override def execute(action: Action): Unit = {
    actions = actions :+ action
    lastAction = Some(actions.last)
    action match {
      case a: ChangeModeAction => a.execute
      case _ =>
    }
  }
}
