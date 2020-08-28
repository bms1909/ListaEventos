package com.brunomusskopf.listaeventos.remote.checkInEvento.dataSourceImpl

import com.brunomusskopf.listaeventos.data.checkInEvento.dataSource.CheckInRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoResponse
import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils
import com.brunomusskopf.listaeventos.remote.checkInEvento.service.CheckInApi

class CheckInRemoteDataSourceImpl(private val api: CheckInApi) : CheckInRemoteDataSourceI {

    override suspend fun checkIn(request: CheckInEventoRequest): CheckInEventoResponse? =
        RetrofitBaseUtils
            .executaCall(api.checkin(request))

}