package com.brunomusskopf.listaeventos.data.checkInEvento.repositoryImpl

import com.brunomusskopf.listaeventos.data.checkInEvento.dataSource.CheckInRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoResponse
import com.brunomusskopf.listaeventos.domain.checkInEvento.repository.CheckInEventoRepositoryI

class CheckInEventoRepositoryImpl(private val remoteDataSource: CheckInRemoteDataSourceI) :
    CheckInEventoRepositoryI {

    override suspend fun checkIn(request: CheckInEventoRequest): CheckInEventoResponse? =
        remoteDataSource.checkIn(request)
}