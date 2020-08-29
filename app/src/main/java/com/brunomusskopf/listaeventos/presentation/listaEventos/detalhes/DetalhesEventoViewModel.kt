package com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listaEventos.interactor.BuscaEventosUseCase
import com.brunomusskopf.listaeventos.presentation.listaEventos.EventoViewMapper
import com.brunomusskopf.listaeventos.presentation.listaEventos.model.EventoView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalhesEventoViewModel(
    private val useCase: BuscaEventosUseCase,
    private val mapper: EventoViewMapper
) : ViewModel() {

    val liveData: MutableLiveData<EventoView?> = MutableLiveData()
    val liveDataProgress: MutableLiveData<Boolean> = MutableLiveData()

    fun iniciaBuscaEvento(idEvento: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            liveDataProgress.value = true
            val evento = useCase.buscaEvento(idEvento)
            liveData.value = mapper.mapToView(evento)
            liveDataProgress.value = false
        }
    }
}