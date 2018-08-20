package org.midityping.poc.app

import java.io.File

import org.midityping.poc.actions.{DefaultActionExecutor, DefaultActionFactory}
import org.midityping.poc.events.EventQueue
import org.midityping.poc.logging.Logger
import org.midityping.poc.mapping.Mapper
import org.midityping.poc.mapping.storage.FileMappingStorage
import org.midityping.poc.midi.MidiEventListener
import org.midityping.poc.system.{DefaultEventHandler, DefaultStrikeListener, MidiTypingSystem}

object MidiTypingApp {
  val logger = Logger.forClass(getClass)

  def main(args: Array[String]): Unit = {
    val mapper = new Mapper
    val eventListener = new MidiEventListener
    val actionFactory = new DefaultActionFactory
    val actionExecutor = new DefaultActionExecutor
    val strikeListener = new DefaultStrikeListener(mapper, actionFactory, actionExecutor)
    val eventQueue = new EventQueue(strikeListener, 100)
    val eventHandler = new DefaultEventHandler(eventQueue)
    val system = new MidiTypingSystem(eventListener, eventHandler, mapper, actionFactory, actionExecutor)

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

    system.start
    Thread.sleep(Long.MaxValue)
  }
}
