package com.brunomusskopf.listaeventos.domain.listEvents.interactor

import com.brunomusskopf.listaeventos.domain.listEvents.model.Event
import com.brunomusskopf.listaeventos.domain.listEvents.repository.EventsRepositoryI

class LoadEventsUseCase(private val repository: EventsRepositoryI) {

    //acredito que useCases devem estar presentes mesmo em casos de mero encaminhamento como estes
    //pois deixam claro ao desenvolvedor que não há nenhuma regra de negócio aos dados do repository
    suspend fun loadEvents() : List<Event>? = repository.loadEvents()

    suspend fun loadEvent(id : Int) : Event?  = repository.loadEvent(id)

}