package org.midityping.poc.app

import org.eclipse.jetty.websocket.api.{Session, WebSocketAdapter}
import org.midityping.poc.logging.aLogger
import org.midityping.poc.system.MidiTypingSystem
import org.midityping.poc.system.events.{SystemEvent, SystemEventType}

class MainWebSocket(system: MidiTypingSystem) extends WebSocketAdapter {
  private val logger = aLogger.forClass(getClass)

  override def onWebSocketConnect(sess: Session): Unit = {
    super.onWebSocketConnect(sess)
    logger.info("onWebSocketConnect")

    //send current mode
    sess.getRemote.sendString(toJson(SystemEvent(SystemEventType.ModeChange, system.currentMode)))

    system.subscribe((event: SystemEvent) => {
      if (sess.isOpen) {
        sess.getRemote.sendString(toJson(event))
      }
    })
  }

  def toJson(event: SystemEvent): String = {
    //TODO: write proper json
    s"""{"eventType":"${event.eventType}","arg1":"${event.arg1}"}"""
  }

  override def onWebSocketError(cause: Throwable): Unit = {
    super.onWebSocketError(cause)
    logger.error("onWebSocketError", cause)
  }

  override def onWebSocketText(message: String): Unit = {
    super.onWebSocketText(message)
    logger.info("onWebSocketText")
  }

  override def onWebSocketClose(statusCode: Int, reason: String): Unit = {
    super.onWebSocketClose(statusCode, reason)
    logger.info("onWebSocketClose")
  }
}
