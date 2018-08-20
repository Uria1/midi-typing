package org.midityping.poc.system

import org.midityping.poc.events.{Event, EventHandler, EventQueue}
import org.slf4j.LoggerFactory

class DefaultEventHandler(eventQueue: EventQueue) extends EventHandler {
  val logger = LoggerFactory.getLogger(getClass)

  override def message(event: Event): Unit = {
    if (event.velocity > 3) {
      eventQueue.enqueue(event)
    } else {
      logger.info(s"ignored: velocity = ${event.velocity}")
    }
  }
}
