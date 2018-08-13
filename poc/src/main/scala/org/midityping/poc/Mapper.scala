package org.midityping.poc

class Mapper(mapping: Mapping) {
  def getActionFor(event: Event): Option[ActionDescriptor] = {
    event match {
      case ev: MidiEvent => ev.note match {
        case Note(_, num) => mapping.map.get(EventDescriptor(EventType.MidiNoteOn, num.toString))
      }
    }
  }

}
