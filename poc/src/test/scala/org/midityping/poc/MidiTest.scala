package org.midityping.poc

import javax.sound.midi._
import org.specs2.mutable.SpecificationWithJUnit

import scala.util.Try

class MidiTest extends SpecificationWithJUnit {
  "Midi" should {

    "work" in {
      val devices = MidiSystem.getMidiDeviceInfo()
      devices.foreach(deviceInfo => {
        Try {
          println(s"###### $deviceInfo #######")
          Thread.sleep(1000)
          val device = MidiSystem.getMidiDevice(deviceInfo)
          device.getTransmitter.setReceiver(new R)
          device.open()
          Thread.sleep(2000)
          device.close()
        }.recover {
          case ex: MidiUnavailableException =>
          case ex => ex.printStackTrace()
        }
      })
      ko
    }
  }

  class R extends Receiver {
    override def send(message: MidiMessage, timeStamp: Long): Unit = {
      message match {
        case sm: ShortMessage =>
          println(s"channel: ${sm.getChannel}")
          println(s"command: ${sm.getCommand}")
          println(s"D1: ${sm.getData1}")
          println(s"D2: ${sm.getData2}")
        case _ => println(message)
      }
      println(message.getMessage)
    }

    override def close(): Unit = {

    }
  }

}

