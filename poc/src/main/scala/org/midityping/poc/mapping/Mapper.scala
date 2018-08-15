package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.common.Note
import org.midityping.poc.events.{Event, EventDescriptor, EventType}

class Mapper(mapping: Mapping) {
  def getActionDescriptorFor(event: Event): Option[ActionDescriptor] = {
    event match {
      case ev: Event => ev.note match {
        case Note(_, _, num) => mapping.map.get(EventDescriptor(EventType.MidiNoteOn, num.toString).asKey)
      }
    }
  }
}
