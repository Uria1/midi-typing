package org.midityping.poc

import org.specs2.mutable.SpecificationWithJUnit
import org.midityping.poc.midi.MidiEventListener

class E2ETest extends SpecificationWithJUnit {
  "A Note" should {
    "trigger A key press" in {
      val device = new MidiTestDevice

      val midiListener = new MidiEventListener()
      val handler = new TestEventHandler
      midiListener.subscribe(handler)
      midiListener.start
      eventually {
        device.sendEvent
        handler.messages.size mustNotEqual 0
        handler.messages.toList.foreach(m => {
          println(m)
        })
        handler.messages.size mustEqual (999)
      }
    }
  }
}
