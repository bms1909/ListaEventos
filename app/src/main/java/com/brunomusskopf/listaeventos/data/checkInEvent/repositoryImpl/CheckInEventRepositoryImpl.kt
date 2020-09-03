package com.brunomusskopf.listaeventos.data.checkInEvent.repositoryImpl

import com.brunomusskopf.listaeventos.data.checkInEvent.dataSource.CheckInRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventResponse
import com.brunomusskopf.listaeventos.domain.checkInEvent.repository.CheckInEventRepositoryI

class CheckInEventRepositoryImpl(private val remoteDataSource: CheckInRemoteDataSourceI) :
    CheckInEventRepositoryI {

    override suspend fun checkIn(request: CheckInEventRequest): CheckInEventResponse? =
        remoteDataSource.checkIn(request)
}