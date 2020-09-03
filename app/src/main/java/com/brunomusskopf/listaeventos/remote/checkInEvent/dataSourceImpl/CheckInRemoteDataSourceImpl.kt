package com.brunomusskopf.listaeventos.remote.checkInEvent.dataSourceImpl

import com.brunomusskopf.listaeventos.data.checkInEvent.dataSource.CheckInRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventResponse
import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils
import com.brunomusskopf.listaeventos.remote.checkInEvent.service.CheckInApi

class CheckInRemoteDataSourceImpl(private val api: CheckInApi) : CheckInRemoteDataSourceI {

    override suspend fun checkIn(request: CheckInEventRequest): CheckInEventResponse? =
        RetrofitBaseUtils
            .executeCall(api.checkin(request))

}