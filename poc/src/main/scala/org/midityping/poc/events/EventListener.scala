package org.midityping.poc.events

trait EventListener {
  def subscribe(eventHandler: EventHandler): Unit
}
