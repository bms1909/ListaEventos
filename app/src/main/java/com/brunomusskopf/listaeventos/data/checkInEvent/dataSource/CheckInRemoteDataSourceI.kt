package com.brunomusskopf.listaeventos.data.checkInEvent.dataSource

import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventResponse

interface CheckInRemoteDataSourceI {
    suspend fun checkIn(request: CheckInEventRequest): CheckInEventResponse?
}