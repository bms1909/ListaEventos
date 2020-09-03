package com.brunomusskopf.listaeventos.domain.checkInEvent.repository

import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventResponse

interface CheckInEventRepositoryI {

    suspend fun checkIn(request: CheckInEventRequest): CheckInEventResponse?

}