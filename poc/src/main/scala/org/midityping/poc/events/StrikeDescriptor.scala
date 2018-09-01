package org.midityping.poc.events

import org.midityping.poc.common.Mode

case class StrikeDescriptor(eventDescriptors: Seq[EventDescriptor], mode: String) {
  def asKey: String = s"$mode:" + eventDescriptors.map(_.asKey).mkString(",")
}

object StrikeDescriptor {
  def apply(eventDescriptor: EventDescriptor, mode: String): StrikeDescriptor = {
    new StrikeDescriptor(Seq(eventDescriptor), mode)
  }

  def apply(arg: String, mode: String = Mode.default): StrikeDescriptor = StrikeDescriptor(EventDescriptor(arg), mode)
}
