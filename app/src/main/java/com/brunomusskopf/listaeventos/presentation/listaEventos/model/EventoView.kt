package com.brunomusskopf.listaeventos.presentation.listaEventos.model

import com.brunomusskopf.listaeventos.domain.listaEventos.model.Cupom
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Pessoa

data class EventoView(
    val id: Int?,
    val people: List<Pessoa>?,
    val date: String?,
    val description: String?,
    val image: String?,
    val longitude: Double?,
    val latitude: Double?,
    val price: String?,
    val title: String?,
    val cupons: List<Cupom>?
)