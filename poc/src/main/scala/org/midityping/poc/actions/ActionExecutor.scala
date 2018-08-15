package org.midityping.poc.actions

trait ActionExecutor {
  def execute(action: Action): Unit
}
