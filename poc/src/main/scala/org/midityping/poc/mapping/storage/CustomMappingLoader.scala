package org.midityping.poc.mapping.storage

import java.io._

import org.midityping.poc.actions.{ActionDescriptor, ActionType}
import org.midityping.poc.events.{EventDescriptor, EventType, StrikeDescriptor}
import org.midityping.poc.mapping.Mapping

import scala.io.{BufferedSource, Source}

object CustomMappingLoader {
  def load(file: File): Mapping = {
    val source = Source.fromFile(file)
    val mapping = load(source)
    source.close()
    mapping
  }

  def load(resourceFilename: String): Mapping = {
    val source = Source.fromURL(getClass.getResource(resourceFilename))
    val mapping = load(source)
    source.close()
    mapping
  }

  private def load(source: BufferedSource): Mapping = {
    val lines = for (line <- source.getLines()) yield line

    val data = lines.filter(_.trim != "").toList.map(line => {
      val keyValue = line.split("\\-\\>")
      if (keyValue.size != 2) {
        throw new Exception(s"Invalid mapping line '$line'")
      }
      (strikeDescriptor(keyValue(0).trim), actionDescriptor(keyValue(1).trim))
    })

    Mapping(data)
  }

  private def strikeDescriptor(s: String): StrikeDescriptor = {
    StrikeDescriptor(s.split(',').map(event => {
      val tokens = event.split(':')
      if (tokens.size == 2) {
        EventDescriptor(tokens(0), tokens(1))
      } else if (tokens.size == 1) {
        EventDescriptor(EventType.MidiNoteOn, tokens(0))
      } else {
        throw new Exception(s"Invalid event descriptor '$s'")
      }

    }))
  }

  private def actionDescriptor(s: String): ActionDescriptor = {
    val tokens = s.split(':')
    if (tokens.size == 2) {
      ActionDescriptor(tokens(0), tokens(1))
    } else if (tokens.size == 1) {
      ActionDescriptor(ActionType.KeyStroke, tokens(0))
    } else {
      throw new Exception(s"Invalid action descriptor '$s'")
    }
  }
}
