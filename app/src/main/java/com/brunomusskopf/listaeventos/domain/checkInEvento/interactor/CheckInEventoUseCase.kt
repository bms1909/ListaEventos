package com.brunomusskopf.listaeventos.domain.checkInEvento.interactor

import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoResponse
import com.brunomusskopf.listaeventos.domain.checkInEvento.repository.CheckInEventoRepositoryI

class CheckInEventoUseCase(private val repository: CheckInEventoRepositoryI) {

    suspend fun validaCampos(request: CheckInEventoRequest): Boolean {
        return false
    }

    suspend fun checkIn(request: CheckInEventoRequest): CheckInEventoResponse? = repository
        .checkIn(request)

}