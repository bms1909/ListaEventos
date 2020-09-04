package com.brunomusskopf.listaeventos.domain.checkInEvent.interactor

import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventResponse
import com.brunomusskopf.listaeventos.domain.checkInEvent.repository.CheckInEventRepositoryI

class CheckInEventRepositoryMock : CheckInEventRepositoryI {

    var mockSuccess : Boolean = false

    override suspend fun checkIn(request: CheckInEventRequest): CheckInEventResponse? {
        val responseMock = CheckInEventResponse()

        responseMock.code = if (mockSuccess) {
            "200"
        } else {
            "400"
        }
        return responseMock
    }
}