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
    val liveDataProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun tentaCheckIn() {
        CoroutineScope(Dispatchers.Main).launch {
            //com escopo main e setValue, notificação a tela não depende de
            // enfileiramento na main thread
            liveDataProgress.value = true

            //liveData nulo neste ponto deve caracterizar uma exception
            val dadosTela = liveData.value!!

            val camposInvalidos = useCase.validaCampos(dadosTela)
            if (camposInvalidos != null) {
                liveDataErroStatus.value = camposInvalidos
                liveDataProgress.value = false
                return@launch
            }

            val objeto = useCase.executaCheckInOuErro(dadosTela)

            if (objeto == null) {
                liveDataErroStatus.value = null
            }
            liveDataProgress.value = false
        }
    }
}