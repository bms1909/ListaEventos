package com.brunomusskopf.listaeventos.inject

import com.brunomusskopf.listaeventos.data.checkInEvento.dataSource.CheckInRemoteDataSourceI
import com.brunomusskopf.listaeventos.data.checkInEvento.repositoryImpl.CheckInEventoRepositoryImpl
import com.brunomusskopf.listaeventos.domain.checkInEvento.interactor.CheckInEventoUseCase
import com.brunomusskopf.listaeventos.domain.checkInEvento.repository.CheckInEventoRepositoryI
import com.brunomusskopf.listaeventos.presentation.checkInEvento.CheckInEventoViewModel
import com.brunomusskopf.listaeventos.remote.checkInEvento.dataSourceImpl.CheckInRemoteDataSourceImpl
import com.brunomusskopf.listaeventos.remote.checkInEvento.service.CheckInApi
import com.brunomusskopf.listaeventos.remote.checkInEvento.service.CheckInApiRetrofitServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CheckInEventoKoinModule {

    fun getModulosCheckInEvento() = listOf(
        getModuloCheckInEventoRemote(),
        getModuloCheckInEventoData(),
        getModuloCheckInEventoDomain(),
        getModuloCheckInEventoPresentation()
    )

    private fun getModuloCheckInEventoRemote() = module {
        factory<CheckInApi> {
            CheckInApiRetrofitServiceFactory.createRetrofitService()
        }
        factory<CheckInRemoteDataSourceI> { CheckInRemoteDataSourceImpl(get()) }
    }

    private fun getModuloCheckInEventoData() = module {
        factory<CheckInEventoRepositoryI> { CheckInEventoRepositoryImpl(get()) }
    }

    private fun getModuloCheckInEventoDomain() = module {
        factory { CheckInEventoUseCase(get()) }
    }

    private fun getModuloCheckInEventoPresentation() = module {
        viewModel { CheckInEventoViewModel(get()) }
    }

}