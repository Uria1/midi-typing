package poc.src.test.scala.org.midityping.poc.testsupport

import org.midityping.poc.common.{MidiChannel, Note}
import org.midityping.poc.events.EventType.EventType
import org.midityping.poc.events.{Event, EventType}

import scala.util.Random

trait TestSupport {
  val random = Random

  def randomInt(from: Int, to: Int) = random.nextInt(to - from) + from

  def randomLong = random.nextLong

  def randomVelocity = randomInt(0, 127)

  def randomNote:Note = Note(randomInt(0,127))

  def anEvent(note: Note = randomNote,
              eventType: EventType = EventType.MidiNoteOn,
              timestamp: Long = randomLong,
              channel: Int = MidiChannel.default,
              velocity: Int = randomVelocity): Event = {
    Event(eventType, timestamp, channel, velocity, note)
  }
}
