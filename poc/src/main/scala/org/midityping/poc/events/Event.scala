package org.midityping.poc.events

import org.midityping.poc.common.Note
import org.midityping.poc.events.EventType.EventType

case class Event(eventType: EventType,
                 timestamp: Long,
                 channel: Int,
                 velocity: Int,
                 note: Note) {

  def asDescriptor: EventDescriptor = {
    EventDescriptor(eventType, note.fullName)
  }
}
