package com.brunomusskopf.listaeventos.data.checkInEvento.dataSource

import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoResponse

interface CheckInRemoteDataSourceI {
    suspend fun checkIn(request: CheckInEventoRequest): CheckInEventoResponse?
}