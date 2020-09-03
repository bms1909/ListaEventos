package com.brunomusskopf.listaeventos.presentation.listEvents.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listEvents.interactor.LoadEventsUseCase
import com.brunomusskopf.listaeventos.presentation.listEvents.EventViewMapper
import com.brunomusskopf.listaeventos.presentation.listEvents.model.EventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListEventsViewModel(
    private val useCase: LoadEventsUseCase,
    private val mapper: EventViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<List<EventView>?> = MutableLiveData()
    val liveDataProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun startEventsSearch() {
        CoroutineScope(Dispatchers.Main).launch {
            liveDataProgress.value = true
            val eventsDomain = useCase.loadEvents()

            liveData.value = mapper.mapToView(eventsDomain)
            liveDataProgress.value = false
        }
    }
}