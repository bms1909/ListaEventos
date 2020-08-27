package com.brunomusskopf.listaeventos.presentation.listaEventos.lista

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento

class ListaEventosViewModel : ViewModel() {

    val liveData : LiveData<List<Evento>> = MutableLiveData()

}