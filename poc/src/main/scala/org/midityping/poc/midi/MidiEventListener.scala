package org.midityping.poc.midi

import javax.sound.midi.{MidiSystem, ShortMessage}
import org.midityping.poc.EventType.EventType
import org.midityping.poc._

class MidiEventListener extends EventListener {
  var handler: Option[EventHandler] = None

  def subscribe(eventHandler: EventHandler) = {
    handler = Some(eventHandler)
  }

  def eventTypeFrom(command: Int): EventType = {
    command match {
      case ShortMessage.NOTE_ON => EventType.MidiNoteOn
      case ShortMessage.NOTE_OFF => EventType.MidiNoteOff
    }
  }

  def message(shortMessage: ShortMessage) = {
    handler.foreach(_.message(Event(
      eventTypeFrom(shortMessage.getCommand),
      shortMessage.getChannel,
      Note("c", shortMessage.getData1, shortMessage.getData2))
    ))
  }

  def start = {
    MidiSystem.getTransmitter.setReceiver(new MidiReceiver(this))
  }
}
