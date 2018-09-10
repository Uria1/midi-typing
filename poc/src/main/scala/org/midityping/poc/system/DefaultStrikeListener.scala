package org.midityping.poc.system

import org.midityping.poc.actions.{ActionDescriptor, ActionExecutor, ActionFactory}
import org.midityping.poc.events.{Strike, StrikeListener}
import org.midityping.poc.logging.aLogger
import org.midityping.poc.mapping.Mapper

class DefaultStrikeListener(mapper: Mapper,
                            actionFactory: ActionFactory,
                            actionExecutor: ActionExecutor,
                            getMode: () => String) extends StrikeListener {
  val logger = aLogger.forClass(getClass)
  var listeners = Seq.empty[ActionListener]

  override def strike(strike: Strike): Unit = {
    val maybeDescriptor = mapper.getActionDescriptor(strike, getMode())
      .orElse(mapper.getActionDescriptor(strike, "*"))

    maybeDescriptor match {
      case Some(descriptor) =>
        logger.info(s"mapped event: $strike -> $descriptor")
        fireActionEvent(descriptor)
        actionExecutor.execute(actionFactory.createAction(descriptor))

      case None =>
        logger.info(s"not mapped: $strike")
        fireUnmappedStrikeEvent(strike, getMode())
    }
  }

  def fireActionEvent(descriptor: ActionDescriptor) = {
    listeners.foreach(_.action(descriptor))
  }

  def fireUnmappedStrikeEvent(strike: Strike, mode: String): Unit = {
    listeners.foreach(_.unmappedStrike(strike, mode))
  }

  def subscribe(listener: ActionListener): Unit = {
    listeners = listeners :+ listener
  }
}
