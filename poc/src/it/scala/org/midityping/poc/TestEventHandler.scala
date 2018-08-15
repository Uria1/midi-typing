package poc.src.it.scala.org.midityping.poc

import org.midityping.poc.events.{Event, EventHandler}

import scala.collection.mutable.ListBuffer

class TestEventHandler extends EventHandler {
  val messages = ListBuffer.empty[Event]

  override def message(event: Event): Unit = {
    messages += event
  }
}
