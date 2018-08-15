package org.midityping.poc.actions

trait ActionFactory {
  def createAction(descriptor: ActionDescriptor): Action
}
