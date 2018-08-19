package org.midityping.poc.events

trait StrikeListener {
  def strike(strike: Strike): Unit
}
