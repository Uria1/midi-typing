package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, ActionFactory}
import org.midityping.poc.events.{EventHandler, EventListener}
import org.midityping.poc.mapping.Mapper

class MidiTypingSystem(eventListener: EventListener,
                       eventHandler: EventHandler,
                       mapper: Mapper,
                       actionFactory: ActionFactory,
                       actionExecutor: ActionExecutor) {

  eventListener.subscribe(eventHandler)

  def loadMappingResource(resourcePath: String) = {
    mapper.appendMapping(JsonMappingLoader.load(resourcePath))
  }

}
