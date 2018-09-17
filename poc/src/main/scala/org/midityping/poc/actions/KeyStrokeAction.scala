package org.midityping.poc.actions

import java.awt.event.KeyEvent

import org.midityping.poc.actions.awt.AWTKeyMapper
import org.midityping.poc.logging.aLogger

case class KeyStrokeAction(key: String, modifiers: Modifiers = Modifiers.None) extends Action {
  val logger = aLogger.forClass(getClass)

  def modifierKeyCode(modifier: Modifier): Int = {
    modifier match {
      case ShiftModifier => KeyEvent.VK_SHIFT
    }
  }

  override def execute: Unit = {
    val keyCode = AWTKeyMapper.mapKey(key)
    logger.trace(s"key $key mapped to AWT code $keyCode")

    modifiers.list.foreach(modifier => {
      AWTRobot.robot.keyPress(modifierKeyCode(modifier))
    })

    AWTRobot.robot.keyPress(keyCode)
    AWTRobot.robot.keyRelease(keyCode)

    modifiers.list.foreach(modifier => {
      AWTRobot.robot.keyRelease(modifierKeyCode(modifier))
    })
  }
}
