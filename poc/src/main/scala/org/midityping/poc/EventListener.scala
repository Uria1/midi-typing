package org.midityping.poc

trait EventListener {
  def subscribe(eventHandler: EventHandler): Unit
}
