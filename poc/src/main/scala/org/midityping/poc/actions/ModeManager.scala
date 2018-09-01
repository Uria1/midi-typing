package org.midityping.poc.actions

import org.midityping.poc.common.Mode

class ModeManager {
  private var mode = Mode.default

  def currentMode = mode

  def currentMode_=(newMode: String): Unit = mode = newMode
}
