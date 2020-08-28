package com.brunomusskopf.listaeventos.remote.checkInEvento.service

import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils

object CheckInApiRetrofitServiceFactory {
    fun createRetrofitService() = RetrofitBaseUtils
        .criarRetrofitPadrao()
        .create(CheckInApi::class.java)
}