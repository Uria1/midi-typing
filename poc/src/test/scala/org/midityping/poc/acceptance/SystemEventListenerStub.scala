package poc.src.test.scala.org.midityping.poc.acceptance

import org.midityping.poc.system.events.{SystemEvent, SystemEventListener}

class SystemEventListenerStub extends SystemEventListener {
  var events: Seq[SystemEvent] = Seq.empty

  override def onevent(event: SystemEvent): Unit = {
    events = events :+ event
  }

  def lastEvent: Option[SystemEvent] = {
    events.lastOption
  }

  def lastEvent(ofType: String): Option[SystemEvent] = {
    events.filter(_.eventType == ofType).lastOption
  }
}
