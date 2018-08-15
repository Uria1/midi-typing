package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.{Event, EventDescriptor, EventType}

class Mapper(var mapping: Mapping) {
  def getActionDescriptorFor(event: Event): Option[ActionDescriptor] = {
    event match {
      case ev: Event =>
        mapping.map.get(EventDescriptor(EventType.MidiNoteOn, ev.note.fullName).asKey)
    }
  }
}

object Mapper {
  def empty: Mapper = new Mapper(Mapping(Map.empty))
}
