package org.midityping.poc

import org.midityping.poc.midi.EventHandler

import scala.collection.mutable.ListBuffer

class TestEventHandler extends EventHandler {
  val messages = ListBuffer.empty[String]

  override def message(s: String): Unit = {
    messages += s
  }
}
