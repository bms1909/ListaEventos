package com.brunomusskopf.listaeventos.data.listEvents.repositoryImpl

import com.brunomusskopf.listaeventos.data.listEvents.dataSource.EventsRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.listEvents.model.Event
import com.brunomusskopf.listaeventos.domain.listEvents.repository.EventsRepositoryI


class EventsRepositoryImpl(
    private val dataSourceRemote : EventsRemoteDataSourceI) : EventsRepositoryI {

    //Desta maneira, a implementação de uma origem de dados alternativa (como um cache)
    //é muito facilitada
    override suspend fun loadEvents(): List<Event>? = dataSourceRemote.loadEvents()

    override suspend fun loadEvent(id: Int): Event? = dataSourceRemote.loadEvent(id)

}