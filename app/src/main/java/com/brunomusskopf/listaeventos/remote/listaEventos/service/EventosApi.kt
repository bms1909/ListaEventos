package com.brunomusskopf.listaeventos.remote.listaEventos.service

import com.brunomusskopf.listaeventos.remote.listaEventos.model.EventoRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface EventosApi {

    @GET("events")
    fun getEventos() : List<EventoRemote>

    @GET("events/{id}")
    fun getEvento(@Path("id") id: Int) : EventoRemote
}