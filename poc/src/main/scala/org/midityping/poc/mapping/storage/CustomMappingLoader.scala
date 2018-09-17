package org.midityping.poc.mapping.storage

import java.io._

import org.midityping.poc.actions.{ActionDescriptor, Modifiers}
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

    val lineList = lines.filter(_.trim != "").toList
    val line1 = lineList.head

    val mode = if (line1.startsWith("mode:")) {
      line1.substring("mode:".length)
    } else {
      throw new Exception(s"First line in mapping file must indicate the mode. be i.e. 'mode:default'")
    }

    val data = lineList.tail.map(line => {
      val keyValue = line.split("\\-\\>")
      if (keyValue.size != 2) {
        throw new Exception(s"Invalid mapping line '$line'")
      }
      (strikeDescriptor(mode, keyValue(0).trim), actionDescriptor(keyValue(1).trim))
    })

    Mapping(data)
  }

  private def strikeDescriptor(mode: String, s: String): StrikeDescriptor = {
    StrikeDescriptor(s.split(',').map(event => {
      val tokens = event.split(':').map(_.trim)
      if (tokens.size == 2) {
        EventDescriptor(tokens(0), tokens(1))
      } else if (tokens.size == 1) {
        EventDescriptor(EventType.MidiNoteOn, tokens(0))
      } else {
        throw new Exception(s"Invalid event descriptor '$s'")
      }

    }), mode)
  }

  private def actionDescriptor(s: String): ActionDescriptor = {
    val tokens = s.split(':')
    val (actionType, token) = if (tokens.size == 2) {
      (tokens(0), tokens(1))
    } else if (tokens.size == 1) {
      ("KeyStroke", tokens(0))
    } else {
      throw new Exception(s"Invalid action descriptor '$s'")
    }

    val (parsedToken, modifiers) = parseActionToken(token)
    ActionDescriptor(actionType, parsedToken, modifiers)
  }

  private def parseActionToken(token: String): (String, Modifiers) = {
    if (token.length >= 1 && token.startsWith("%")) {
      (token.substring(1), Modifiers.Shift)
    } else {
      (token, Modifiers.None)
    }
  }
}
