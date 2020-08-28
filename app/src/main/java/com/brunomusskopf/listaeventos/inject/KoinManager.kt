package com.brunomusskopf.listaeventos.inject

import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.data.listaEventos.repositoryImpl.EventosRepositoryImpl
import com.brunomusskopf.listaeventos.domain.listaEventos.interactor.BuscaEventosUseCase
import com.brunomusskopf.listaeventos.domain.listaEventos.repository.EventosRepositoryI
import com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes.DetalhesEventoViewModel
import com.brunomusskopf.listaeventos.presentation.listaEventos.lista.ListaEventosViewModel
import com.brunomusskopf.listaeventos.remote.listaEventos.dataSourceImpl.EventosRemoteDataSourceImpl
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApi
import com.brunomusskopf.listaeventos.remote.listaEventos.service.RetrofitEventosServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object KoinManager {

    fun getModulosAplicacao() =
        listOf(
            getModuloBaseRemote(),
            getModuloListaEventosRemote(),
            getModuloListaEventosData(),
            getModuloListaEventosDomain(),
            getModuloListaEventosPresentation()
        )

    fun getModuloBaseRemote() = module {
        factory<EventosApi> {
            RetrofitEventosServiceFactory.createRetrofitService()
        }
    }

    fun getModuloListaEventosRemote() = module {
        factory<EventosRemoteDataSourceI> { EventosRemoteDataSourceImpl(get()) }
    }

    fun getModuloListaEventosData() = module {
        factory<EventosRepositoryI> { EventosRepositoryImpl(get()) }
    }

    fun getModuloListaEventosDomain() = module {
        factory { BuscaEventosUseCase(get()) }
    }

    fun getModuloListaEventosPresentation() = module {
        viewModel { ListaEventosViewModel(get()) }
        viewModel { DetalhesEventoViewModel(get()) }
    }
}


