package org.midityping.poc.app

import org.eclipse.jetty.websocket.api.{Session, WebSocketAdapter}
import org.midityping.poc.logging.aLogger
import org.midityping.poc.system.MidiTypingSystem
import org.midityping.poc.system.events.SystemEvent

class MainWebSocket(system: MidiTypingSystem) extends WebSocketAdapter {
  private val logger = aLogger.forClass(getClass)

  override def onWebSocketConnect(sess: Session): Unit = {
    super.onWebSocketConnect(sess)
    sess.getRemote.sendString("you just connected!")
    logger.info("onWebSocketConnect")

    system.subscribe((event: SystemEvent) => {
      if (sess.isOpen) {
        sess.getRemote.sendString(s"${event.eventType}: ${event.arg1}")
      }
    })
  }

  override def onWebSocketError(cause: Throwable): Unit = {
    super.onWebSocketError(cause)
    logger.error("onWebSocketError", cause)
  }

  override def onWebSocketText(message: String): Unit = {
    super.onWebSocketText(message)
    logger.info("onWebSocketText")
    getRemote.sendString(s"hi... you said $message")
  }

  override def onWebSocketClose(statusCode: Int, reason: String): Unit = {
    super.onWebSocketClose(statusCode, reason)
    logger.info("onWebSocketClose")
  }
}
