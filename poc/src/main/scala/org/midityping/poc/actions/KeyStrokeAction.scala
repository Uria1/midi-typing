package org.midityping.poc.actions

import org.midityping.poc.actions.awt.AWTKeyMapper
import org.midityping.poc.logging.aLogger

case class KeyStrokeAction(key: String) extends Action {
  val logger = aLogger.forClass(getClass)

  override def execute: Unit = {
    val keyCode = AWTKeyMapper.mapKey(key)
    logger.trace(s"key $key mapped to AWT code $keyCode")
    AWTRobot.robot.keyPress(keyCode)
    AWTRobot.robot.keyRelease(keyCode)
  }
}
