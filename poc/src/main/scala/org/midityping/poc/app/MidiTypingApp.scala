package org.midityping.poc.app

import java.io.File

import org.midityping.poc.actions.DefaultActionExecutor
import org.midityping.poc.app.server.MainServer
import org.midityping.poc.logging.aLogger
import org.midityping.poc.mapping.storage.FileMappingStorage
import org.midityping.poc.system.MidiTypingSystem

object MidiTypingApp {
  val logger = aLogger.forClass(getClass)

  def main(args: Array[String]): Unit = {
    val system = new MidiTypingSystem(new DefaultActionExecutor)
    loadMapping(system)

    val server = new MainServer
    server.start

    system.start

    Thread.sleep(Long.MaxValue)
  }

  private def loadMapping(system: MidiTypingSystem) = {
    val storageDir = new File("midi-typing/mappings/")
    if (!storageDir.exists()) {
      logger.info("creating dir " + storageDir.getAbsolutePath)
      val created = storageDir.mkdirs()
      logger.info(s"dir ${storageDir.getAbsolutePath} created? $created")
    }
    val mappingStorage = new FileMappingStorage(storageDir)
    mappingStorage.load().foreach(mapping => {
      system.loadMapping(mapping)
    })
  }
}
