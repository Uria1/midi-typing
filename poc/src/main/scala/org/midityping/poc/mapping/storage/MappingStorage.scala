package org.midityping.poc.mapping.storage

import org.midityping.poc.mapping.Mapping

trait MappingStorage {
  def load(): Seq[Mapping]
}
