package com.brunomusskopf.listaeventos.data.listaEventos.dataSource

import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento

interface EventosRemoteDataSourceI {

    suspend fun buscaEventos() : List<Evento>?

    suspend fun buscaEvento(id : Int) : Evento?

}