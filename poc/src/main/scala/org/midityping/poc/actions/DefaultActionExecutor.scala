package org.midityping.poc.actions

class DefaultActionExecutor extends ActionExecutor {
  override def execute(action: Action): Unit = action.execute
}
