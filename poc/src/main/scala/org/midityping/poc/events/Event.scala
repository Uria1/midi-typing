package org.midityping.poc.events

import org.midityping.poc.common.Note
import org.midityping.poc.events.EventType.EventType

case class Event(eventType: EventType,
                 timestamp: Long,
                 channel: Int,
                 velocity: Int,
                 note: Note) extends Ordered[Event] {

  def asDescriptor: EventDescriptor = {
    EventDescriptor(eventType, note.fullName)
  }

  override def compare(that: Event): Int = {
    this.note.number.compareTo(that.note.number)
  }
}
