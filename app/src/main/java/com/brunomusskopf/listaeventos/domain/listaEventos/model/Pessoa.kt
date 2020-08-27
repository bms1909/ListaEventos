package com.brunomusskopf.listaeventos.domain.listaEventos.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Pessoa {
    var id: Int? = null
    var eventId: Int? = null
    var name: String? = null
    var picture: String? = null
}