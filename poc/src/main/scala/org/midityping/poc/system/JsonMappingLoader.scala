package org.midityping.poc.system

import java.io.{BufferedReader, InputStreamReader, Reader}
import java.util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.midityping.poc.actions.ActionDescriptor
import org.midityping.poc.mapping.Mapping

import scala.collection.JavaConverters._

class JsonMappingLoader {
  def load(resourceFilename: String): Mapping = {
    val resource = getClass.getResourceAsStream(resourceFilename)
    load(new BufferedReader(new InputStreamReader(resource)))
  }


  private def load(reader: Reader): Mapping = {
    val gson = new GsonBuilder().create
    val mapType = new TypeToken[util.Map[String, ActionDescriptor]] {}.getType
    val map = gson.fromJson[util.Map[String, ActionDescriptor]](reader, mapType)
    Mapping(map.asScala)
  }
}
