package com.brunomusskopf.listaeventos.remote.listaEventos.service

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitEventosServiceFactory {

    fun createRetrofitService() = Retrofit
        .Builder()
        .baseUrl("https://5b840ba5db24a100142dcd8c.mockapi.io/api/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build().create(EventosApi::class.java)
}