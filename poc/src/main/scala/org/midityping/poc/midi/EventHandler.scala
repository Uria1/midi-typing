package org.midityping.poc.midi

trait EventHandler {
  def message(s: String): Unit
}
