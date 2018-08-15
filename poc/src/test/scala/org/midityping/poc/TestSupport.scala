package poc.src.test.scala.org.midityping.poc

import org.midityping.poc.common.{MidiChannel, Note}
import org.midityping.poc.events.EventType.EventType
import org.midityping.poc.events.{Event, EventType}

import scala.util.Random

trait TestSupport {
  val random = Random

  def randomInt(from: Int, to: Int) = random.nextInt(to - from) + from

  def randomVelocity = randomInt(0, 127)

  def anEvent(note: Note,
              eventType: EventType = EventType.MidiNoteOn,
              channel: Int = MidiChannel.default,
              velocity: Int = randomVelocity): Event = {
    Event(eventType, channel, velocity, note)
  }
}
