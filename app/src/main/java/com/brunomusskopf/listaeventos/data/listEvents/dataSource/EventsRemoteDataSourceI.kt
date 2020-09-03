package com.brunomusskopf.listaeventos.data.listEvents.dataSource

import com.brunomusskopf.listaeventos.domain.listEvents.model.Event

interface EventsRemoteDataSourceI {

    suspend fun loadEvents() : List<Event>?

    suspend fun loadEvent(id : Int) : Event?

}