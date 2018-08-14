package org.midityping.poc

class DefaultEventHandler(mapper: Mapper, actionFactory: ActionFactory, actionExecutor: ActionExecutor) extends EventHandler {
  override def message(event: Event): Unit = {
    mapper.getActionDescriptorFor(event) match {
      case Some(descriptor) =>
        actionExecutor.execute(actionFactory.createAction(descriptor))
      case None =>
    }
  }
}
