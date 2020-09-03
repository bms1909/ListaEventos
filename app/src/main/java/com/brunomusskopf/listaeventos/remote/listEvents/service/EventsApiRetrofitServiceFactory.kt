package com.brunomusskopf.listaeventos.remote.listEvents.service

import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils

object EventsApiRetrofitServiceFactory {

    fun createRetrofitService() = RetrofitBaseUtils
        .createDefaultRetrofit()
        .create(EventsApi::class.java)
}