package org.midityping.poc.midi

import javax.sound.midi.{MidiMessage, Receiver, ShortMessage}

import scala.util.Try

class MidiReceiver(listener: MidiEventListener) extends Receiver {
  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    Try {
      message match {
        case sm: ShortMessage =>
          println(s"message: ${sm.getCommand} ${sm.getData1} ${sm.getData2}")
          listener.message(sm)
        case _ =>
      }
    }.recover {
      case ex => ex.printStackTrace()
    }
  }

  override def close(): Unit = {

  }
}
