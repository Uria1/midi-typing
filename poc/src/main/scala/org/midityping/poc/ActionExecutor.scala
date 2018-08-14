package org.midityping.poc

trait ActionExecutor {
  def execute(action: Action): Unit
}
