package com.brunomusskopf.listaeventos.presentation.listaEventos.detalhes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listaEventos.interactor.BuscaEventosUseCase
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalhesEventoViewModel(private val useCase: BuscaEventosUseCase) : ViewModel() {

    val liveData: MutableLiveData<Evento?> = MutableLiveData()

    fun iniciaBuscaEvento(idEvento: Int) {
        CoroutineScope(Dispatchers.Default).launch {
            val evento = useCase.buscaEvento(idEvento)
            liveData.postValue(evento)
        }
    }
}