package org.midityping.poc.actions

case class ChangeModeAction(mode: String, modeManager: ModeManager) extends Action {
  override def execute: Unit = {
    modeManager.currentMode = mode
  }
}
