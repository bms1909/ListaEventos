package com.brunomusskopf.listaeventos.domain.checkInEvento.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CheckInEventoResponse {
    val code: String? = null
}