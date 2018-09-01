package org.midityping.poc.system

import org.midityping.poc.actions.{ActionExecutor, ActionFactory}
import org.midityping.poc.events.{Strike, StrikeListener}
import org.midityping.poc.logging.aLogger
import org.midityping.poc.mapping.Mapper

class DefaultStrikeListener(mapper: Mapper,
                            actionFactory: ActionFactory,
                            actionExecutor: ActionExecutor,
                            getMode: () => String) extends StrikeListener {
  val logger = aLogger.forClass(getClass)

  override def strike(strike: Strike): Unit = {
    mapper.getActionDescriptor(strike, getMode()) match {
      case Some(descriptor) =>
        println(s"mapped strike: $strike -> $descriptor")
        logger.info(s"mapped event: $strike -> $descriptor")
        actionExecutor.execute(actionFactory.createAction(descriptor))
      case None => println(s"not mapped: $strike"); logger.info(s"not mapped: $strike")
    }
  }
}
