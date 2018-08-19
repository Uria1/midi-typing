package org.midityping.poc.events

import org.midityping.poc.events.EventType.EventType

case class EventDescriptor(eventType: String, arg: String) {
  def asKey = {
    s"$eventType:$arg"
  }
}

object EventDescriptor {
  def apply(eventType: EventType, arg: String): EventDescriptor = new EventDescriptor(eventType.toString, arg)
  def apply(arg: String): EventDescriptor = new EventDescriptor(EventType.MidiNoteOn.toString, arg)
}
