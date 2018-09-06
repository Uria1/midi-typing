package org.midityping.poc.app.websocket

import org.eclipse.jetty.websocket.servlet.{ServletUpgradeRequest, ServletUpgradeResponse, WebSocketCreator}

import scala.collection.JavaConverters._

class MainWebSocketCreator extends WebSocketCreator {
  override def createWebSocket(servletUpgradeRequest: ServletUpgradeRequest, servletUpgradeResponse: ServletUpgradeResponse): AnyRef = {
    servletUpgradeRequest.getSubProtocols.asScala.foreach(subProtocol => {
      servletUpgradeResponse.setAcceptedSubProtocol(subProtocol)
    })
    new MainWebSocket
  }
}
