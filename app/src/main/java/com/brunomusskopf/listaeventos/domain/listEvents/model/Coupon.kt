package com.brunomusskopf.listaeventos.domain.listEvents.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Coupon {
    var id: Int? = null
    var eventId: Int? = null
    var discount: String? = null
}