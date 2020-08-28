package com.brunomusskopf.listaeventos.remote.listaEventos.service

import com.brunomusskopf.listaeventos.remote.base.RetrofitBaseUtils

object RetrofitEventosServiceFactory {

    fun createRetrofitService() = RetrofitBaseUtils
        .criarRetrofitPadrao()
        .create(EventosApi::class.java)


}