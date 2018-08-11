package org.midityping.poc.midi

import javax.sound.midi.MidiSystem

class MidiEventListener {
  var handler: Option[EventHandler] = None

  def subscribe(eventHandler: EventHandler) = {
    handler = Some(eventHandler)
  }

  def message(s: String) = {
    handler.foreach(_.message(s))
  }

  def start = {
    MidiSystem.getTransmitter.setReceiver(new MidiReceiver(this))
  }


}
