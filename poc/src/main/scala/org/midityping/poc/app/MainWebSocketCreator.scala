package org.midityping.poc.app

import org.eclipse.jetty.websocket.servlet.{ServletUpgradeRequest, ServletUpgradeResponse, WebSocketCreator}
import org.midityping.poc.system.MidiTypingSystem

import scala.collection.JavaConverters._

class MainWebSocketCreator(system: MidiTypingSystem) extends WebSocketCreator {
  override def createWebSocket(servletUpgradeRequest: ServletUpgradeRequest, servletUpgradeResponse: ServletUpgradeResponse): AnyRef = {
    servletUpgradeRequest.getSubProtocols.asScala.foreach(subProtocol => {
      servletUpgradeResponse.setAcceptedSubProtocol(subProtocol)
    })
    new MainWebSocket(system)
  }
}
