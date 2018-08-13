package org.midityping.poc

import com.fasterxml.jackson.annotation.JsonProperty
import org.midityping.poc.EventType.EventType

case class EventDescriptor(@JsonProperty eventType: EventType, @JsonProperty arg: String)
