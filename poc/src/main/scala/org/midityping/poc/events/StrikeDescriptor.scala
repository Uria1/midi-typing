package org.midityping.poc.events

case class StrikeDescriptor(eventDescriptors: Seq[EventDescriptor]) {
  def asKey: String = eventDescriptors.map(_.asKey).mkString(",")
}

object StrikeDescriptor {
  def apply(eventDescriptor: EventDescriptor): StrikeDescriptor = new StrikeDescriptor(Seq(eventDescriptor))

  def apply(arg: String): StrikeDescriptor = StrikeDescriptor(EventDescriptor(arg))
}
