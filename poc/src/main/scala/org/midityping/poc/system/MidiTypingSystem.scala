package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, DefaultActionFactory, ModeManager}
import org.midityping.poc.events.{EventQueue, Strike}
import org.midityping.poc.mapping.storage.CustomMappingLoader
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.midityping.poc.midi.MidiEventListener
import org.midityping.poc.system.events.{SystemEvent, SystemEventListener, SystemEventType}

class MidiTypingSystem(val actionExecutor: ActionExecutor) {
  private var systemEventListeners = Seq.empty[SystemEventListener]
  private val modeManager = new ModeManager

  modeManager.subscribe((mode: String) => {
    fireSystemEvent(SystemEvent(SystemEventType.ModeChange, mode))
  })

  val mapper = new Mapper
  val eventListener = new MidiEventListener
  val actionFactory = new DefaultActionFactory(modeManager)
  val strikeListener = new DefaultStrikeListener(mapper, actionFactory, actionExecutor, () => modeManager.currentMode)
  val eventQueue = new EventQueue(100)
  eventQueue.subscribe(strikeListener)

  eventQueue.subscribe((strike: Strike) => {
    fireSystemEvent(SystemEvent(SystemEventType.NoteStrike, strike.events.map(_.note.fullName).mkString(",")))
  })

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

  def subscribe(listener: SystemEventListener) = {
    systemEventListeners = systemEventListeners :+ listener
  }

  def fireSystemEvent(event: SystemEvent): Unit = {
    systemEventListeners.foreach(_.onevent(event))
  }
}
