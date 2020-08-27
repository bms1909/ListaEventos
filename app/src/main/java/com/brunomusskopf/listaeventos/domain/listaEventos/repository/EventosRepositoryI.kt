package com.brunomusskopf.listaeventos.domain.listaEventos.repository

import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento

interface EventosRepositoryI {

    suspend fun buscaEventos() : List<Evento>?

    suspend fun buscaEvento(id : Int) : Evento?

}