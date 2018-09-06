package org.midityping.poc.app.websocket

import org.eclipse.jetty.websocket.api.{Session, WebSocketAdapter}
import org.midityping.poc.logging.aLogger

class MainWebSocket extends WebSocketAdapter {
  private val logger = aLogger.forClass(getClass)

  override def onWebSocketConnect(sess: Session): Unit = {
    super.onWebSocketConnect(sess)
    sess.getRemote.sendString("you just connected!")
    logger.info("onWebSocketConnect")

    while (true) {
      Thread.sleep(2000)
      if (sess.isOpen) {
        sess.getRemote.sendString(System.currentTimeMillis().toString)
      }
    }
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
