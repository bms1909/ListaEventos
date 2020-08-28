package com.brunomusskopf.listaeventos.data.listaEventos.repositoryImpl

import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import com.brunomusskopf.listaeventos.domain.listaEventos.repository.EventosRepositoryI


class EventosRepositoryImpl(
    private val dataSourceRemote : EventosRemoteDataSourceI) : EventosRepositoryI {

    //Desta maneira, a implementação de uma origem de dados alternativa (como um cache)
    //é muito facilitada
    override suspend fun buscaEventos(): List<Evento>? = dataSourceRemote.buscaEventos()

    override suspend fun buscaEvento(id: Int): Evento? = dataSourceRemote.buscaEvento(id)

}