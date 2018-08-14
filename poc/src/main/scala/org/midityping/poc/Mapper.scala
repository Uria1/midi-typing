package org.midityping.poc

class Mapper(mapping: Mapping) {
  def getActionDescriptorFor(event: Event): Option[ActionDescriptor] = {
    event match {
      case ev: Event => ev.note match {
        case Note(_, num, _) => mapping.map.get(EventDescriptor(EventType.MidiNoteOn, num.toString).asKey)
      }
    }
  }

}
