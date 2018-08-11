package org.midityping.poc

trait Event

case class MidiEvent(note: Note) extends Event