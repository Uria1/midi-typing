package org.midityping.poc

import org.midityping.poc.EventType.EventType

case class Event(eventType: EventType, channel: Int, velocity: Int, note: Note)
