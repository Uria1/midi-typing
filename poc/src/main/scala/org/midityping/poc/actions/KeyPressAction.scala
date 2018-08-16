package org.midityping.poc.actions

import org.midityping.poc.actions.awt.AWTKeyMapper
import org.midityping.poc.logging.Logger

case class KeyPressAction(key: String) extends Action {
  val logger = Logger.forClass(getClass)

  override def execute: Unit = {
    val keyCode = AWTKeyMapper.mapKey(key)
    logger.debug(s"key $key mapped to AWT code $keyCode")
    AWTRobot.robot.keyPress(keyCode)
    AWTRobot.robot.keyRelease(keyCode)
  }
}
