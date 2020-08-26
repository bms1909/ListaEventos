package com.brunomusskopf.listaeventos.data.listaEventos.dataSource

import com.brunomusskopf.listaeventos.remote.listaEventos.model.EventoRemote

interface EventosRemoteDataSourceI {

    suspend fun buscaEventos() : List<EventoRemote>

    suspend fun buscaEvento(id : Int) : EventoRemote

}