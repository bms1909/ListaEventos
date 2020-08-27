package com.brunomusskopf.listaeventos.remote.listaEventos.service

import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import retrofit2.http.GET
import retrofit2.http.Path

interface EventosApi {

    @GET("events")
    fun getEventos() : List<Evento>

    @GET("events/{id}")
    fun getEvento(@Path("id") id: Int) : Evento
}