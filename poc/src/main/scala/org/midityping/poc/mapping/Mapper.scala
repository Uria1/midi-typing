package org.midityping.poc.mapping

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.common.Mode
import org.midityping.poc.events.{Strike, StrikeDescriptor}

class Mapper {
  private var mappings: Seq[Mapping] = Seq.empty

  def appendMapping(mapping: Mapping): Unit = {
    mappings = mappings :+ mapping
  }

  def getActionDescriptor(strike: Strike, mode: String = Mode.default): Option[ActionDescriptor] = {
    val strikeD = StrikeDescriptor(strike.events.map(_.asDescriptor), mode)

    mappings.collectFirst({
      case mapping if mapping.data.exists(_._1.asKey == strikeD.asKey) =>
        mapping.data.find(_._1.asKey == strikeD.asKey)
    }).flatten.map(_._2)
  }
}

object Mapper {
  def withMapping(mapping: Mapping): Mapper = {
    val m = new Mapper()
    m.appendMapping(mapping)
    m
  }
}
