package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.StrikeDescriptor

case class Mapping1(map: scala.collection.Map[String, ActionDescriptor])

case class Mapping(data: Seq[(StrikeDescriptor, ActionDescriptor)])

object Mapping {
  def apply(item: (StrikeDescriptor, ActionDescriptor)): Mapping = new Mapping(Seq(item))
}
