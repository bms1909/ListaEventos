package com.brunomusskopf.listaeventos.presentation.listEvents.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listEvents.interactor.LoadEventsUseCase
import com.brunomusskopf.listaeventos.presentation.listEvents.EventViewMapper
import com.brunomusskopf.listaeventos.presentation.listEvents.model.EventView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventDetailViewModel(
    private val useCase: LoadEventsUseCase,
    private val mapper: EventViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<EventView?> = MutableLiveData()
    val liveDataProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun startEventSearch(idEvento: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            liveDataProgress.value = true
            val evento = useCase.loadEvent(idEvento)
            liveData.value = mapper.mapToView(evento)
            liveDataProgress.value = false
        }
    }
}