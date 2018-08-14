package org.midityping.poc

import java.io.{BufferedReader, InputStreamReader}
import java.util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import scala.collection.JavaConverters._
//import collection.JavaConversions._
import collection.mutable._

class JsonMappingLoader {
  def load(resourceFilename: String): Mapping = {
    val gson = new GsonBuilder().create

    val m = Mapping(Map(
      EventDescriptor(EventType.MidiNoteOn, "85").asKey -> ActionDescriptor(ActionType.KeyPress, "a")
    ))

    val mapType = new TypeToken[util.Map[String, ActionDescriptor]] {}.getType

    gson.toJson(m.map.asJava, mapType)

    val resource = getClass.getResourceAsStream(resourceFilename)
    val map = gson.fromJson[util.Map[String, ActionDescriptor]](new BufferedReader(new InputStreamReader(resource)), mapType)
    Mapping(map.asScala)
  }
}

//{"key1":{"eventType":"MidiNoteOn","arg":"85"},"value1":{"actionType":"KeyPress","arg":"a"}}
//{"key1":{"eventType":"MidiNoteOn","arg":"85"},"value1":{"actionType":"KeyPress","arg":"a"},"key2":{"eventType":"MidiNoteOn","arg":"86"},"value2":{"actionType":"KeyPress","arg":"b"}}