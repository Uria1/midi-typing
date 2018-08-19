package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, ActionFactory}
import org.midityping.poc.events.{EventHandler, EventListener}
import org.midityping.poc.mapping.storage.JsonMappingLoader
import org.midityping.poc.mapping.{Mapper1, Mapping1}

class MidiTypingSystem(eventListener: EventListener,
                       eventHandler: EventHandler,
                       mapper: Mapper1,
                       actionFactory: ActionFactory,
                       actionExecutor: ActionExecutor) {
  def start: Unit = {
    eventListener.start
  }

  eventListener.subscribe(eventHandler)

  def loadMappingResource(resourcePath: String): Unit = {
    mapper.appendMapping(JsonMappingLoader.load(resourcePath))
  }

  def loadMapping(mapping: Mapping1): Unit = {
    mapper.appendMapping(mapping)
  }
}
