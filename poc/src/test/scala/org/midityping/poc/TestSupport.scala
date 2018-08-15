package poc.src.test.scala.org.midityping.poc

import org.midityping.poc.common.MidiChannel

import scala.util.Random

trait TestSupport {
  val defaultMidiChannel = MidiChannel.default

  val random = Random

  def randomInt(from: Int, to: Int) = random.nextInt(to - from) + from

  def randomVelocity = randomInt(0, 127)

}
