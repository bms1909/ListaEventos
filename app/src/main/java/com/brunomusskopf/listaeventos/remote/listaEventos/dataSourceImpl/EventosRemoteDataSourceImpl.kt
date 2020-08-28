package com.brunomusskopf.listaeventos.remote.listaEventos.dataSourceImpl

import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApi

class EventosRemoteDataSourceImpl(private val api : EventosApi) : EventosRemoteDataSourceI {

    override suspend fun buscaEventos(): List<Evento>? = RetrofitBaseUtils
        .executaCall(api.getEventos())

    override suspend fun buscaEvento(id: Int): Evento? = RetrofitBaseUtils
        .executaCall(api.getEvento(id))
}