package org.midityping.poc.mapping.storage

import java.io._

import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.events.{EventDescriptor, StrikeDescriptor}
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

    val data = lines.toSeq.map(line => {
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
      if (tokens.size != 2) {
        throw new Exception(s"Invalid event descriptor '$s'")
      }
      EventDescriptor(tokens(0), tokens(1))
    }))
  }

  private def actionDescriptor(s: String): ActionDescriptor = {
    val tokens = s.split(':')
    if (tokens.size != 2) {
      throw new Exception(s"Invalid action descriptor '$s'")
    }
    ActionDescriptor(tokens(0), tokens(1))
  }
}
