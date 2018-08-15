package org.midityping.poc.events

trait EventHandler {
  def message(event: Event): Unit
}
