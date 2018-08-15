package org.midityping.poc

case class KeyPressAction(key: String) extends Action {
  override def execute: Unit = {
    //AWTRobot.robot.keyPress()
  }
}
