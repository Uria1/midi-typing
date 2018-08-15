package org.midityping.poc.actions.awt

import java.awt.event.KeyEvent

object AWTKeyMapper {
  def mapKey(key: String): Int = {
    classOf[KeyEvent].getField("VK_" + key).getInt(null)
  }
}
