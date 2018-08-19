package poc.src.test.scala.org.midityping.poc.actions

import org.midityping.poc.events.{Event, EventQueue, Strike, StrikeListener}
import org.specs2.matcher.Scope
import org.specs2.mutable.SpecificationWithJUnit
import poc.src.test.scala.org.midityping.poc.TestSupport

class EventQueueTest extends SpecificationWithJUnit with TestSupport {

  trait Context extends Scope {
    val listener = new TestStrikeListener
    val strikeTimeWindow = randomInt(100, 500)
    val q = new EventQueue(listener, strikeTimeWindow)

    def enqueueEventOnTime(event: Event, previousEvent: Option[Event] = None) = {
      previousEvent match {
        case Some(prevEvent) => Thread.sleep(event.timestamp - prevEvent.timestamp)
        case _ =>
      }
      q.enqueue(event)
    }
  }

  "EventQueue" >> {

    "route a single event" in new Context {
      val event = anEvent()
      q.enqueue(event)
      eventually {
        listener.strikes.head.events.head === event
      }
    }

    "route two events as a single strike" in new Context {
      val event1 = anEvent(timestamp = 100L)
      val event2 = anEvent(timestamp = 100L + strikeTimeWindow / 2)

      q.enqueue(event1)
      q.enqueue(event2)

      eventually {
        listener.strikes.head.events.head === event1
        listener.strikes.head.events.tail.head === event2
      }
    }

    "route three events as 2 strikes" in new Context {
      val window1event1 = anEvent(timestamp = 100L)
      val window1event2 = anEvent(timestamp = 100L + strikeTimeWindow / 2)
      val window2event1 = anEvent(timestamp = 100L + strikeTimeWindow + 100)

      enqueueEventOnTime(window1event1)
      enqueueEventOnTime(window1event2, Some(window1event1))
      enqueueEventOnTime(window2event1, Some(window1event2))

      eventually {
        listener.strikes.head.events.size === 2
        listener.strikes.head.events.head === window1event1
        listener.strikes.head.events.tail.head === window1event2
        listener.strikes.tail.head.events.size === 1
        listener.strikes.tail.head.events.head === window2event1
      }
    }
  }

  class TestStrikeListener extends StrikeListener {
    var strikes: Seq[Strike] = Seq.empty

    override def strike(strike: Strike): Unit = {
      strikes = strikes :+ strike
    }
  }

}
