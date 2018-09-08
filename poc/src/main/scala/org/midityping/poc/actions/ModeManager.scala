package org.midityping.poc.actions

import org.midityping.poc.common.Mode

class ModeManager {
  private var mode = Mode.default
  private var listeners = Seq.empty[ModeChangeListener]

  def currentMode = mode

  def currentMode_=(newMode: String): Unit = {
    mode = newMode
    listeners.foreach(_.modeChanged(newMode))
  }

  def subscribe(listener: ModeChangeListener) = {
    listeners = listeners :+ listener
  }
}
