package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, ActionFactory}
import org.midityping.poc.events.{Event, EventHandler}
import org.midityping.poc.mapping.Mapper
import org.slf4j.LoggerFactory

class DefaultEventHandler(mapper: Mapper, actionFactory: ActionFactory, actionExecutor: ActionExecutor) extends EventHandler {
  val logger = LoggerFactory.getLogger(getClass)

  override def message(event: Event): Unit = {
    mapper.getActionDescriptorFor(event) match {
      case Some(descriptor) =>
        println(s"mapped event: $event -> $descriptor")
        logger.info(s"mapped event: $event -> $descriptor")
        actionExecutor.execute(actionFactory.createAction(descriptor))
      case None => logger.info(s"not mapped: $event")
    }
  }
}
