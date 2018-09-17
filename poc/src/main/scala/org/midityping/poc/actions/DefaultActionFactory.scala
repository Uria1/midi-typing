package org.midityping.poc.actions

import org.midityping.poc.logging.aLogger

class DefaultActionFactory(modeManager: ModeManager) extends ActionFactory {
  val logger = aLogger.forClass(getClass)

  override def createAction(descriptor: ActionDescriptor): Action = {
    logger.trace(s"creating action for $descriptor")
    ActionType.withName(descriptor.actionType) match {
      case ActionType.KeyPress =>
        KeyPressAction(descriptor.arg)
      case ActionType.KeyRelease =>
        KeyReleaseAction(descriptor.arg)
      case ActionType.KeyStroke =>
        KeyStrokeAction(descriptor.arg, descriptor.modifiers)
      case ActionType.ChangeMode =>
        ChangeModeAction(descriptor.arg, modeManager)
    }
  }
}
