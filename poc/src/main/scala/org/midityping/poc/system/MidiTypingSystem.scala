package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, DefaultActionFactory, ModeManager}
import org.midityping.poc.events.EventQueue
import org.midityping.poc.mapping.storage.CustomMappingLoader
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.midityping.poc.midi.MidiEventListener

class MidiTypingSystem(val actionExecutor: ActionExecutor) {
  val modeManager = new ModeManager
  val mapper = new Mapper
  val eventListener = new MidiEventListener
  val actionFactory = new DefaultActionFactory(modeManager)
  val strikeListener = new DefaultStrikeListener(mapper, actionFactory, actionExecutor, () => modeManager.currentMode)
  val eventQueue = new EventQueue(100)
  eventQueue.subscribe(strikeListener)
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

  def currentMode = modeManager.currentMode
}
