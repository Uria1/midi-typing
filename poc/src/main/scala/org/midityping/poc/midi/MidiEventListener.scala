package org.midityping.poc.midi

import javax.sound.midi.{MidiSystem, ShortMessage}
import org.midityping.poc.common.Note
import org.midityping.poc.events.EventType.EventType
import org.midityping.poc.events.{Event, EventHandler, EventListener, EventType}
import org.midityping.poc.logging.Logger

class MidiEventListener extends EventListener {
  val logger = Logger.forClass(getClass)
  var handlers: Seq[EventHandler] = Seq.empty

  def subscribe(eventHandler: EventHandler) = {
    handlers = handlers :+ eventHandler
  }

  def eventTypeFrom(command: Int): EventType = {
    command match {
      case ShortMessage.NOTE_ON => EventType.MidiNoteOn
      case ShortMessage.NOTE_OFF => EventType.MidiNoteOff
      case _ =>
        logger.trace(s"ignoring midi command $command")
        EventType.Other
    }
  }

  def message(shortMessage: ShortMessage) = {
    val eventType = eventTypeFrom(shortMessage.getCommand)
    eventType match {
      case EventType.MidiNoteOn =>
        sendEventToHandlers(Event(
          eventTypeFrom(shortMessage.getCommand),
          System.currentTimeMillis(),
          shortMessage.getChannel,
          shortMessage.getData2,
          Note(shortMessage.getData1))
        )
      case _ =>
        logger.trace(s"ignored midi event ${shortMessage.getCommand} ($eventType)")
    }
  }

  def triggerEvent(event:Event) = sendEventToHandlers(event)

  private def sendEventToHandlers(event: Event): Unit = {
    handlers.foreach(_.message(event))
  }

  def start = {
    MidiSystem.getTransmitter.setReceiver(new MidiReceiver(this))
  }
}
