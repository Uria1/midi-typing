package org.midityping.poc.events

trait EventListener {
  def start: Unit

  def subscribe(eventHandler: EventHandler): Unit
}
