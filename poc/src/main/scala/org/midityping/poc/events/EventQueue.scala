package org.midityping.poc.events

import java.util.concurrent.ConcurrentLinkedQueue

import scala.collection.JavaConverters._
import scala.util.Try

class EventQueue(listener: StrikeListener, strikeTimeWindow: Int) {
  val events = new ConcurrentLinkedQueue[Event]
  var windowStartTime = 0L
  val lock = new Object

  val strikeThread = new Thread(() => {
    while (true) {
      lock.synchronized {
        lock.wait()
      }
      Thread.sleep(strikeTimeWindow)
      listener.strike(flushEventsAsStrike())
    }
  })

  strikeThread.start()

  def enqueue(event: Event): Unit = {
    val emptyQueue = events.isEmpty
    events.add(event)
    if (emptyQueue)
      lock.synchronized {
        lock.notify()
      }
  }

  //  private def timeWindowConcluded: Boolean = {
  //    System.currentTimeMillis() - windowStartTime > strikeTimeWindow
  //  }

  private def flushEventsAsStrike(): Strike = {
    val strike = Strike(events.asScala.toSeq)
    events.clear()
    strike
  }

}
