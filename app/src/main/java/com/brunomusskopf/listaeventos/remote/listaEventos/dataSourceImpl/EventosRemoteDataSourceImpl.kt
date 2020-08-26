package com.brunomusskopf.listaeventos.remote.listaEventos.dataSourceImpl

import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.remote.listaEventos.model.EventoRemote
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApi

class EventosRemoteDataSourceImpl(private val api : EventosApi) : EventosRemoteDataSourceI {

    override suspend fun buscaEventos(): List<EventoRemote> {
        return api.getEventos()
    }

    override suspend fun buscaEvento(id: Int): EventoRemote = api.getEvento(id)
}