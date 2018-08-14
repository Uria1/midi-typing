package org.midityping.poc

class DefaultActionExecutor extends ActionExecutor {
  override def execute(action: Action): Unit = action.execute
}
