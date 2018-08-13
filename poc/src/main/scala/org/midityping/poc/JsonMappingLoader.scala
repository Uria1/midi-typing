package org.midityping.poc

import java.io.File

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

class JsonMappingLoader {
  def load(resourceFilename: String): Mapping = {
    val om = new ObjectMapper with ScalaObjectMapper
    om.registerModule(DefaultScalaModule)
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    val m = Mapping(Map(
      EventDescriptor(EventType.MidiNoteOn, "85") -> ActionDescriptor(ActionType.KeyPress, "a")
    ))
    om.writeValue(new File("/tmp/json.json"), m)
    val resource = getClass.getClassLoader.getResource(resourceFilename)
    om.readValue(resource, classOf[Mapping])
  }

}
