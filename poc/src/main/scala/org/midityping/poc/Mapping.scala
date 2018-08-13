package org.midityping.poc

import com.fasterxml.jackson.annotation.JsonProperty

case class Mapping(@JsonProperty map: Map[EventDescriptor, ActionDescriptor]) {

}
