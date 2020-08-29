package com.brunomusskopf.listaeventos.presentation.listaEventos

import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import com.brunomusskopf.listaeventos.presentation.base.toDefaultStringDate
import com.brunomusskopf.listaeventos.presentation.listaEventos.model.EventoView

class EventoViewMapper {

    fun mapToView(eventoDomain: Evento?): EventoView? {
        eventoDomain ?: return null
        return EventoView(
            id = eventoDomain.id,
            people = eventoDomain.people,
            cupons = eventoDomain.cupons,
            date = eventoDomain.date?.toDefaultStringDate(),
            description = eventoDomain.description,
            image = eventoDomain.image,
            latitude = eventoDomain.latitude,
            longitude = eventoDomain.longitude,
            price = eventoDomain.price,
            title = eventoDomain.title
        )
    }

    fun mapToView(eventosDomain: List<Evento>?): List<EventoView>? {
        return eventosDomain?.map { mapToView(it)!! }
    }
}