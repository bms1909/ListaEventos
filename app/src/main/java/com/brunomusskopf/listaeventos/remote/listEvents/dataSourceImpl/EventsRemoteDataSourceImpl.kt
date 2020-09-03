package com.brunomusskopf.listaeventos.remote.listEvents.dataSourceImpl

import com.brunomusskopf.listaeventos.data.listEvents.dataSource.EventsRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.listEvents.model.Event
import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils
import com.brunomusskopf.listaeventos.remote.listEvents.service.EventsApi

class EventsRemoteDataSourceImpl(private val api : EventsApi) : EventsRemoteDataSourceI {

    override suspend fun loadEvents(): List<Event>? = RetrofitBaseUtils
        .executeCall(api.getEvents())

    override suspend fun loadEvent(id: Int): Event? = RetrofitBaseUtils
        .executeCall(api.getEvent(id))
}