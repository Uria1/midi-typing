package org.midityping.poc.app

import org.eclipse.jetty.server.{Server, ServerConnector}
import org.eclipse.jetty.servlet.{ServletContextHandler, ServletHolder}

class MainServer {
  val server = new Server
  val connector = new ServerConnector(server)
  connector.setPort(8002)
  server.addConnector(connector)

  val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
  context.setContextPath("/")
  server.setHandler(context)

  val holderEvents = new ServletHolder("ws-events", classOf[MainWebSocketServlet]);
  context.addServlet(holderEvents, "/events/*");

  def start = {
    server.start()
    server.dump(System.err)
    server.join()
  }
}
