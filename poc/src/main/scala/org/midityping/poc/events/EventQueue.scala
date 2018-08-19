package org.midityping.poc.events

import java.util.concurrent.ConcurrentLinkedQueue

import org.midityping.poc.logging.Logger

import scala.collection.JavaConverters._

class EventQueue(listener: StrikeListener, strikeTimeWindow: Int) {
  val logger = Logger.forClass(getClass)
  val events = new ConcurrentLinkedQueue[Event]
  var windowStartTime = 0L
  val lock = new Object
  val eventQueueLock = new Object

  val strikeThread = new Thread(() => {
    while (true) {
      lock.synchronized {
        if (events.isEmpty) {
          logger.trace("sleeping...")
          lock.wait()
        }
      }
      logger.trace("finished sleeping, start window sleep. size" + events.size())
      Thread.sleep(strikeTimeWindow)
      logger.trace("end window sleep, size" + events.size())
      listener.strike(flushEventsAsStrike())
    }
  })

  strikeThread.start()

  def enqueue(event: Event): Unit = {
    lock.synchronized {
      eventQueueLock.synchronized {
        val emptyQueue = events.isEmpty
        events.add(event)
        if (emptyQueue) {
          logger.trace("notify, size: " + events.size())
          lock.notify()
        }
      }
    }
  }

  private def flushEventsAsStrike(): Strike = {
    eventQueueLock.synchronized {
      val strike = Strike(events.asScala.toSeq)
      events.clear()
      logger.trace("strike , size: " + strike.events.size)
      strike
    }
  }

}
