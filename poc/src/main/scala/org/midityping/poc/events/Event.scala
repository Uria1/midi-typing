package org.midityping.poc.events

import org.midityping.poc.common.Note
import org.midityping.poc.events.EventType.EventType

case class Event(eventType: EventType, channel: Int, velocity: Int, note: Note)
