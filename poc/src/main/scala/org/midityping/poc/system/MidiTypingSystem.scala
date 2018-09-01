package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, DefaultActionFactory}
import org.midityping.poc.common.Mode
import org.midityping.poc.events.EventQueue
import org.midityping.poc.mapping.storage.CustomMappingLoader
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.midityping.poc.midi.MidiEventListener

class MidiTypingSystem(val actionExecutor: ActionExecutor) {
  private var mode = Mode.default

  def currentMode = mode

  def currentMode_=(newMode: String): Unit = mode = newMode

  val mapper = new Mapper
  val eventListener = new MidiEventListener
  val actionFactory = new DefaultActionFactory
  val strikeListener = new DefaultStrikeListener(mapper, actionFactory, actionExecutor, () => currentMode)
  val eventQueue = new EventQueue(strikeListener, 100)
  val eventHandler = new DefaultEventHandler(eventQueue)

  def start: Unit = {
    eventListener.start
  }

  eventListener.subscribe(eventHandler)

  def loadMappingResource(resourcePath: String): Unit = {
    mapper.appendMapping(CustomMappingLoader.load(resourcePath))
  }

  def loadMapping(mapping: Mapping): Unit = {
    mapper.appendMapping(mapping)
  }
}
