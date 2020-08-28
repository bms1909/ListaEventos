package com.brunomusskopf.listaeventos.presentation.checkInEvento

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.checkInEvento.interactor.CheckInEventoUseCase
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoValidation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckInEventoViewModel(private val useCase: CheckInEventoUseCase) : ViewModel() {

    val liveData: MutableLiveData<CheckInEventoRequest> = MutableLiveData()
    val liveDataErroStatus: MutableLiveData<CheckInEventoValidation?> = MutableLiveData()
    //TODO aplicar em tela
    val liveDataProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun tentaCheckIn() {
        CoroutineScope(Dispatchers.Default).launch {
            liveDataProgress.postValue(true)

            //liveData nulo neste ponto deve caracterizar uma exception
            val dadosTela = liveData.value!!

            val camposInvalidos = useCase.validaCampos(dadosTela)
            if (camposInvalidos != null) {
                liveDataErroStatus.postValue(camposInvalidos)
                liveDataProgress.postValue(false)
                return@launch
            }

            val objeto = useCase.executaCheckInOuErro(dadosTela)

            if (objeto == null) {
                liveDataErroStatus.postValue(null)
            }
            liveDataProgress.postValue(false)
        }
    }
}