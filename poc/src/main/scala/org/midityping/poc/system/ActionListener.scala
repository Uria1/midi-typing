package org.midityping.poc.system

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.Strike

trait ActionListener {
  def unmappedStrike(strike: Strike, mode: String): Unit

  def action(descriptor: ActionDescriptor): Unit
}
