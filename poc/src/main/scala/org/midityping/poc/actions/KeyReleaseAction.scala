package org.midityping.poc.actions

import org.midityping.poc.actions.awt.AWTKeyMapper

case class KeyReleaseAction(key: String) extends Action {
  override def execute: Unit = {
    val keyCode = AWTKeyMapper.mapKey(key)
    AWTRobot.robot.keyRelease(keyCode)
  }
}
