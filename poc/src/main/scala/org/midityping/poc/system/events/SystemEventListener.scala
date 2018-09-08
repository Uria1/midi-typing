package org.midityping.poc.system.events

trait SystemEventListener {
  def onevent(event: SystemEvent): Unit
}
