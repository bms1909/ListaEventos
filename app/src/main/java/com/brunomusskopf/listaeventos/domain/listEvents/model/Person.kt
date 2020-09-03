package com.brunomusskopf.listaeventos.domain.listEvents.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Person {
    var id: Int? = null
    var eventId: Int? = null
    var name: String? = null
    var picture: String? = null
}