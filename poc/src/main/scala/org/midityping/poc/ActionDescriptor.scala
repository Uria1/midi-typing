package org.midityping.poc

import com.fasterxml.jackson.annotation.JsonProperty
import org.midityping.poc.ActionType.ActionType

case class ActionDescriptor(@JsonProperty actionType: ActionType, @JsonProperty arg: String)
