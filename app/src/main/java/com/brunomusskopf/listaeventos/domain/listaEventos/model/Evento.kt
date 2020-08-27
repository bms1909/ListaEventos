package com.brunomusskopf.listaeventos.domain.listaEventos.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Evento {
    var people: List<Pessoa>? = null
    var date: Long? = null
    var description: String? = null
    var image: String? = null
    var longitude: Double? = null
    var latitude: Double? = null
    var price: String? = null
    var title: String? = null
    var id: Int? = null
    var cupons: List<Cupom>? = null
}