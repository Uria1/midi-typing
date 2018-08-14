package org.midityping.poc.midi

import javax.sound.midi.{MidiMessage, Receiver, ShortMessage}

class MidiReceiver(listener: MidiEventListener) extends Receiver {
  override def send(message: MidiMessage, timeStamp: Long): Unit = {
    message match {
      case sm: ShortMessage => listener.message(sm)
      case _ =>
    }
  }

  override def close(): Unit = {

  }
}
