package org.midityping.poc.midi

import javax.sound.midi.{MidiMessage, Receiver, ShortMessage}

class MidiReceiver(listener: MidiEventListener) extends Receiver {
  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    message match {
      case sm: ShortMessage =>
        listener.message(s"channel: ${sm.getChannel} command: ${sm.getCommand} D1: ${sm.getData1} D2: ${sm.getData2}")
      case _ => println(message)
    }
    println(message.getMessage)

  }

  override def close(): Unit = {

  }
}
