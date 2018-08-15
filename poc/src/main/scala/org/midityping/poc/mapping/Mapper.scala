package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.{Event, EventDescriptor, EventType}

class Mapper() {
  private var mappings: Seq[Mapping] = Seq.empty

  def appendMapping(mapping: Mapping): Unit = {
    mappings = mappings :+ mapping
  }

  def getActionDescriptorFor(event: Event): Option[ActionDescriptor] = {
    event match {
      case ev: Event =>
        val key = EventDescriptor(EventType.MidiNoteOn, ev.note.fullName).asKey
        mappings.collectFirst { case mapping if mapping.map.contains(key) => mapping.map(key) }
    }
  }
}

object Mapper {
  def withMapping(mapping: Mapping): Mapper = {
    val m = new Mapper()
    m.appendMapping(mapping)
    m
  }
}