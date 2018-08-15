package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, ActionFactory}
import org.midityping.poc.events.{Event, EventHandler}
import org.midityping.poc.mapping.Mapper

class DefaultEventHandler(mapper: Mapper, actionFactory: ActionFactory, actionExecutor: ActionExecutor) extends EventHandler {
  override def message(event: Event): Unit = {
    mapper.getActionDescriptorFor(event) match {
      case Some(descriptor) =>
        actionExecutor.execute(actionFactory.createAction(descriptor))
      case None =>
    }
  }
}
