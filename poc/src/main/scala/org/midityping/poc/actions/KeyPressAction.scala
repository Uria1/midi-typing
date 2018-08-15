package org.midityping.poc.actions

case class KeyPressAction(key: String) extends Action {
  override def execute: Unit = {
    //AWTRobot.robot.keyPress()
  }
}
