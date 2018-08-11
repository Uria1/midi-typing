package org.midityping.poc

import javax.sound.midi.{MidiSystem, ShortMessage}

class MidiTestDevice {

  def sendEvent = {
    val r = MidiSystem.getReceiver
    val noteOn = new ShortMessage(ShortMessage.NOTE_ON, 1, 58, 90)
    val noteOff = new ShortMessage(ShortMessage.NOTE_OFF, 1, 58, 90)

    r.send(noteOn, -1)
    Thread.sleep(500)
    r.send(noteOff, -1)
  }

}
