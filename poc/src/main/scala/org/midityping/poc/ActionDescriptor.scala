package org.midityping.poc

import org.midityping.poc.ActionType.ActionType

case class ActionDescriptor(actionType: String, arg: String)

object ActionDescriptor {
  def apply(actionType: ActionType, arg: String): ActionDescriptor = new ActionDescriptor(actionType.toString, arg)
}