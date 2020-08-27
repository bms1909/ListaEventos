package com.brunomusskopf.listaeventos.domain.listaEventos.model

open class Evento(
    val people: List<Pessoa>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: String,
    val title: String,
    val id: Int,
    val cupons: List<Cupom>
)