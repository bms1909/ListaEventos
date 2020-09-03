package com.brunomusskopf.listaeventos.domain.listEvents.repository

import com.brunomusskopf.listaeventos.domain.listEvents.model.Event

interface EventsRepositoryI {

    suspend fun loadEvents() : List<Event>?

    suspend fun loadEvent(id : Int) : Event?

}