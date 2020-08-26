package com.brunomusskopf.listaeventos.inject

import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.remote.listaEventos.dataSourceImpl.EventosRemoteDataSourceImpl
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApi
import com.brunomusskopf.listaeventos.remote.listaEventos.service.RetrofitEventosServiceFactory
import org.koin.dsl.module


object KoinManager {

    fun getModulosBaseRemote() = module {
        factory<EventosApi> {
            RetrofitEventosServiceFactory.createRetrofitService()
        }
    }

    fun getModulosListaEventosRemote() = module {
        factory<EventosRemoteDataSourceI> { EventosRemoteDataSourceImpl(get()) }
    }
}


