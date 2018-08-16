package org.midityping.poc.events

object EventType extends Enumeration {
  type EventType = Value
  val MidiNoteOn, MidiNoteOff, Other = Value
}
