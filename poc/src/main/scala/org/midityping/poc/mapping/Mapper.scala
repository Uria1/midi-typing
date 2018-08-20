package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.{Strike, StrikeDescriptor}

class Mapper {
  private var mappings: Seq[Mapping] = Seq.empty

  def appendMapping(mapping: Mapping): Unit = {
    mappings = mappings :+ mapping
  }

  def getActionDescriptorFor(strike: Strike): Option[ActionDescriptor] = {
    val strikeD = StrikeDescriptor(strike.events.map(_.asDescriptor))

    val mappingWithMatchingStrike = mappings.find(m => {
      m.data.exists(_._1.asKey == strikeD.asKey)
    })

    mappingWithMatchingStrike.flatMap(_.data.collectFirst({
      case (descriptor, action) if descriptor.asKey == strikeD.asKey => action
    }))
  }
}

object Mapper {
  def withMapping(mapping: Mapping): Mapper = {
    val m = new Mapper()
    m.appendMapping(mapping)
    m
  }
}