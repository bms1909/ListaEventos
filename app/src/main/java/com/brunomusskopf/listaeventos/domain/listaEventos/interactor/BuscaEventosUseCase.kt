package com.brunomusskopf.listaeventos.domain.listaEventos.interactor

import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import com.brunomusskopf.listaeventos.domain.listaEventos.repository.EventosRepositoryI

class BuscaEventosUseCase(private val repository: EventosRepositoryI) {

    suspend fun buscaEventos() : List<Evento> = repository.buscaEventos()

    suspend fun buscaEvento(id : Int) : Evento  = repository.buscaEvento(id)

}