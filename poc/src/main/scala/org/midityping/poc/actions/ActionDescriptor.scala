package org.midityping.poc.actions

import org.midityping.poc.actions.ActionType.ActionType

case class ActionDescriptor(actionType: String, arg: String)

object ActionDescriptor {
  def apply(actionType: ActionType, arg: String): ActionDescriptor = new ActionDescriptor(actionType.toString, arg)
  def apply(arg: String): ActionDescriptor = new ActionDescriptor(ActionType.KeyPress.toString, arg)
}