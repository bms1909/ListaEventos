package com.brunomusskopf.listaeventos.inject

import com.brunomusskopf.listaeventos.data.checkInEvent.dataSource.CheckInRemoteDataSourceI
import com.brunomusskopf.listaeventos.data.checkInEvent.repositoryImpl.CheckInEventRepositoryImpl
import com.brunomusskopf.listaeventos.domain.checkInEvent.interactor.CheckInEventUseCase
import com.brunomusskopf.listaeventos.domain.checkInEvent.repository.CheckInEventRepositoryI
import com.brunomusskopf.listaeventos.presentation.checkInEvent.CheckInEventViewModel
import com.brunomusskopf.listaeventos.remote.checkInEvent.dataSourceImpl.CheckInRemoteDataSourceImpl
import com.brunomusskopf.listaeventos.remote.checkInEvent.service.CheckInApi
import com.brunomusskopf.listaeventos.remote.checkInEvent.service.CheckInApiRetrofitServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CheckInEventKoinModule {

    fun getModulesCheckInEvent() = listOf(
        getModuleCheckInEventRemote(),
        getModuleCheckInEventData(),
        getModuleCheckInEventDomain(),
        getModuleCheckInEventPresentation()
    )

    fun getModuleCheckInEventRemote() = module {
        factory<CheckInApi> {
            CheckInApiRetrofitServiceFactory.createRetrofitService()
        }
        factory<CheckInRemoteDataSourceI> { CheckInRemoteDataSourceImpl(get()) }
    }

    fun getModuleCheckInEventData() = module {
        factory<CheckInEventRepositoryI> { CheckInEventRepositoryImpl(get()) }
    }

    fun getModuleCheckInEventDomain() = module {
        factory { CheckInEventUseCase(get()) }
    }

    fun getModuleCheckInEventPresentation() = module {
        viewModel { CheckInEventViewModel(get()) }
    }

}