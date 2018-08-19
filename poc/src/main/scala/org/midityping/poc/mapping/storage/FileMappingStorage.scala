package org.midityping.poc.mapping.storage

import java.io.File

import org.midityping.poc.mapping.Mapping1

class FileMappingStorage(root: File) extends MappingStorage {
  override def load(): Seq[Mapping1] = {
    root.listFiles().map(file => JsonMappingLoader.load(file))
  }
}
