package org.midityping.poc.actions

class DefaultActionFactory extends ActionFactory {
  override def createAction(descriptor: ActionDescriptor): Action = {
    println(s"create action for $descriptor")
    ActionType.withName(descriptor.actionType) match {
      case ActionType.KeyPress =>
        val action = KeyPressAction(descriptor.arg)
        println(s"action: $action")
        action
    }
  }
}
