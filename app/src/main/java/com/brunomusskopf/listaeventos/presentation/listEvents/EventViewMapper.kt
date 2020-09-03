package com.brunomusskopf.listaeventos.presentation.listEvents

import com.brunomusskopf.listaeventos.domain.listEvents.model.Event
import com.brunomusskopf.listaeventos.presentation.base.toDefaultStringDate
import com.brunomusskopf.listaeventos.presentation.listEvents.model.EventView

class EventViewMapper {

    fun mapToView(eventDomain: Event?): EventView? {
        eventDomain ?: return null
        return EventView(
            id = eventDomain.id,
            people = eventDomain.people,
            cupons = eventDomain.cupons,
            date = eventDomain.date?.toDefaultStringDate(),
            description = eventDomain.description,
            image = eventDomain.image,
            latitude = eventDomain.latitude,
            longitude = eventDomain.longitude,
            price = eventDomain.price,
            title = eventDomain.title
        )
    }

    fun mapToView(eventsDomain: List<Event>?): List<EventView>? {
        return eventsDomain?.map { mapToView(it)!! }
    }
}