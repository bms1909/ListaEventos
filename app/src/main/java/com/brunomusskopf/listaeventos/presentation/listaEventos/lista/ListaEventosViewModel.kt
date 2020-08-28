package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listaEventos.interactor.BuscaEventosUseCase
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaEventosViewModel(private val useCase: BuscaEventosUseCase) : ViewModel() {

    val liveData : MutableLiveData<List<Evento>?> = MutableLiveData()
    val liveDataProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun iniciaBuscaEventos() {
        CoroutineScope(Dispatchers.Main).launch {
            liveDataProgress.value = true
            val eventos = useCase.buscaEventos()
            liveData.value = eventos
            liveDataProgress.value = false
        }
    }
}