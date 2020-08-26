package com.brunomusskopf.listaeventos.remote.listaEventos.model

open class EventoRemote(
    val people: List<PessoaRemote>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: String,
    val title: String,
    val id: Int,
    val cupons: List<CupomRemote>
)