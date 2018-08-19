package org.midityping.poc.mapping.storage

import org.midityping.poc.mapping.Mapping1

trait MappingStorage {
  def load(): Seq[Mapping1]
}
