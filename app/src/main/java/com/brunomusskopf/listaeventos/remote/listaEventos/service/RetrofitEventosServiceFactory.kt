package com.brunomusskopf.listaeventos.remote.listaEventos.service

import retrofit2.Retrofit

object RetrofitEventosServiceFactory {

    fun createRetrofitService() = Retrofit
            .Builder()
            .baseUrl("http://5b840ba5db24a100142dcd8c.mockapi.io/api/")
            .build().create(EventosApi::class.java)
}