package org.midityping.poc.events

case class Strike(events: Seq[Event])

object Strike {
  def apply(event: Event): Strike = new Strike(Seq(event))
}