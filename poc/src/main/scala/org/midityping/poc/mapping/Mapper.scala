package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.{Event, EventDescriptor, Strike}

class Mapper() {
  private var mappings: Seq[Mapping] = Seq.empty

  def appendMapping(mapping: Mapping): Unit = {
    mappings = mappings :+ mapping
  }

  def getActionDescriptorFor(strike: Strike): Option[ActionDescriptor] = {
    val event = strike.events.head
    event match {
      case ev: Event =>
        val key = EventDescriptor(event.eventType, ev.note.fullName).asKey
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