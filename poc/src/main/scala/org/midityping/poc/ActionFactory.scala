package org.midityping.poc

trait ActionFactory {
  def createAction(descriptor: ActionDescriptor): Action
}
