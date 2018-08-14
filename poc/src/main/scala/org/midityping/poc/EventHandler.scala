package org.midityping.poc

trait EventHandler {
  def message(event: Event): Unit
}
