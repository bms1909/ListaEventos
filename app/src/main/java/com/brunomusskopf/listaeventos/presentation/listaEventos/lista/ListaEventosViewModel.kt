package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listaEventos.interactor.BuscaEventosUseCase
import com.brunomusskopf.listaeventos.presentation.listaEventos.EventoViewMapper
import com.brunomusskopf.listaeventos.presentation.listaEventos.model.EventoView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaEventosViewModel(
    private val useCase: BuscaEventosUseCase,
    private val mapper: EventoViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<List<EventoView>?> = MutableLiveData()
    val liveDataProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun iniciaBuscaEventos() {
        CoroutineScope(Dispatchers.Main).launch {
            liveDataProgress.value = true
            val eventosDomain = useCase.buscaEventos()

            liveData.value = mapper.mapToView(eventosDomain)
            liveDataProgress.value = false
        }
    }
}