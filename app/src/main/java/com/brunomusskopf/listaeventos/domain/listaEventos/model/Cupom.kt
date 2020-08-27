package com.brunomusskopf.listaeventos.domain.listaEventos.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Cupom {
    var id: Int? = null
    var eventId: Int? = null
    var discount: String? = null
}