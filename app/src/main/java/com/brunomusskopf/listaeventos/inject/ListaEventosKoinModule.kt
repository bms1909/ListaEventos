package com.brunomusskopf.listaeventos.inject

import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.data.listaEventos.repositoryImpl.EventosRepositoryImpl
import com.brunomusskopf.listaeventos.domain.listaEventos.interactor.BuscaEventosUseCase
import com.brunomusskopf.listaeventos.domain.listaEventos.repository.EventosRepositoryI
import com.brunomusskopf.listaeventos.presentation.listaEventos.EventoViewMapper
import com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes.DetalhesEventoViewModel
import com.brunomusskopf.listaeventos.presentation.listaEventos.lista.ListaEventosViewModel
import com.brunomusskopf.listaeventos.remote.listaEventos.dataSourceImpl.EventosRemoteDataSourceImpl
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApi
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApiRetrofitServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ListaEventosKoinModule {

    fun getModulosListaEventos() = listOf(
        getModuloListaEventosRemote(),
        getModuloListaEventosData(),
        getModuloListaEventosDomain(),
        getModuloListaEventosPresentation()
    )

    private fun getModuloListaEventosRemote() = module {
        factory<EventosApi> {
            EventosApiRetrofitServiceFactory.createRetrofitService()
        }
        factory<EventosRemoteDataSourceI> { EventosRemoteDataSourceImpl(get()) }
    }

    private fun getModuloListaEventosData() = module {
        factory<EventosRepositoryI> { EventosRepositoryImpl(get()) }
    }

    private fun getModuloListaEventosDomain() = module {
        factory { BuscaEventosUseCase(get()) }
    }

    private fun getModuloListaEventosPresentation() = module {
        factory { EventoViewMapper() }
        viewModel { ListaEventosViewModel(get(), get()) }
        viewModel { DetalhesEventoViewModel(get(), get()) }
    }

}