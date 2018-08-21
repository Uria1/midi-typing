package org.midityping.poc.actions

import org.midityping.poc.logging.aLogger

class DefaultActionFactory extends ActionFactory {
  override def createAction(descriptor: ActionDescriptor): Action = {
    val logger = aLogger.forClass(getClass)
    logger.trace(s"creating action for $descriptor")
    ActionType.withName(descriptor.actionType) match {
      case ActionType.KeyPress =>
        KeyPressAction(descriptor.arg)
      case ActionType.KeyRelease =>
        KeyReleaseAction(descriptor.arg)
      case ActionType.KeyStroke =>
        KeyStrokeAction(descriptor.arg)
    }
  }
}
