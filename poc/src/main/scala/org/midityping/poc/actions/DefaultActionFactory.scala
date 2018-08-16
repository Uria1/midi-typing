package org.midityping.poc.actions

import org.midityping.poc.logging.Logger

class DefaultActionFactory extends ActionFactory {
  override def createAction(descriptor: ActionDescriptor): Action = {
    val logger = Logger.forClass(getClass)
    logger.trace(s"creating action for $descriptor")
    ActionType.withName(descriptor.actionType) match {
      case ActionType.KeyPress =>
        val action = KeyPressAction(descriptor.arg)
        logger.debug(s"action: $action")
        action
    }
  }
}
