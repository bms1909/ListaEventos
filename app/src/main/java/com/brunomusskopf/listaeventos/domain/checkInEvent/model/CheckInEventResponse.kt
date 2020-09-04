package com.brunomusskopf.listaeventos.domain.checkInEvent.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CheckInEventResponse {
    var code: String? = null
}