package org.midityping.poc

import java.io.{BufferedReader, InputStreamReader}
import java.util

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import scala.collection.JavaConverters._

class JsonMappingLoader {
  def load(resourceFilename: String): Mapping = {
    val gson = new GsonBuilder().create

    val mapType = new TypeToken[util.Map[String, ActionDescriptor]] {}.getType

    val resource = getClass.getResourceAsStream(resourceFilename)
    val map = gson.fromJson[util.Map[String, ActionDescriptor]](new BufferedReader(new InputStreamReader(resource)), mapType)
    Mapping(map.asScala)
  }
}
