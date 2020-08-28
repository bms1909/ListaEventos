package com.brunomusskopf.listaeventos.domain.checkInEvento.repository

import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoResponse

interface CheckInEventoRepositoryI {

    suspend fun checkIn(request: CheckInEventoRequest): CheckInEventoResponse?

}