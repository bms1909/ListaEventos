package com.brunomusskopf.listaeventos.inject

import com.brunomusskopf.listaeventos.data.listEvents.dataSource.EventsRemoteDataSourceI
import com.brunomusskopf.listaeventos.data.listEvents.repositoryImpl.EventsRepositoryImpl
import com.brunomusskopf.listaeventos.domain.listEvents.interactor.LoadEventsUseCase
import com.brunomusskopf.listaeventos.domain.listEvents.repository.EventsRepositoryI
import com.brunomusskopf.listaeventos.presentation.listEvents.EventViewMapper
import com.brunomusskopf.listaeventos.presentation.listEvents.details.EventDetailViewModel
import com.brunomusskopf.listaeventos.presentation.listEvents.list.ListEventsViewModel
import com.brunomusskopf.listaeventos.remote.listEvents.dataSourceImpl.EventsRemoteDataSourceImpl
import com.brunomusskopf.listaeventos.remote.listEvents.service.EventsApi
import com.brunomusskopf.listaeventos.remote.listEvents.service.EventsApiRetrofitServiceFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ListEventsKoinModule {

    fun getModulesListEvents() = listOf(
        getModuleListEventsRemote(),
        getModuleListEventsData(),
        getModuleListEventsDomain(),
        getModuleListEventsPresentation()
    )

    private fun getModuleListEventsRemote() = module {
        factory<EventsApi> {
            EventsApiRetrofitServiceFactory.createRetrofitService()
        }
        factory<EventsRemoteDataSourceI> { EventsRemoteDataSourceImpl(get()) }
    }

    private fun getModuleListEventsData() = module {
        factory<EventsRepositoryI> { EventsRepositoryImpl(get()) }
    }

    private fun getModuleListEventsDomain() = module {
        factory { LoadEventsUseCase(get()) }
    }

    private fun getModuleListEventsPresentation() = module {
        factory { EventViewMapper() }
        viewModel { ListEventsViewModel(get(), get()) }
        viewModel { EventDetailViewModel(get(), get()) }
    }

}