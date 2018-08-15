package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, ActionFactory}
import org.midityping.poc.events.{EventHandler, EventListener}
import org.midityping.poc.mapping.{Mapper, Mapping}
import org.midityping.poc.mapping.storage.JsonMappingLoader

class MidiTypingSystem(eventListener: EventListener,
                       eventHandler: EventHandler,
                       mapper: Mapper,
                       actionFactory: ActionFactory,
                       actionExecutor: ActionExecutor) {
  def start: Unit = {
    eventListener.start
  }

  eventListener.subscribe(eventHandler)

  def loadMappingResource(resourcePath: String): Unit = {
    mapper.appendMapping(JsonMappingLoader.load(resourcePath))
  }

  def loadMapping(mapping: Mapping): Unit = {
    mapper.appendMapping(mapping)
  }
}
