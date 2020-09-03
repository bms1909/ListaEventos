package com.brunomusskopf.listaeventos.remote.checkInEvent.service

import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils

object CheckInApiRetrofitServiceFactory {
    fun createRetrofitService() = RetrofitBaseUtils
        .createDefaultRetrofit()
        .create(CheckInApi::class.java)
}