package com.brunomusskopf.listaeventos.presentation.checkInEvento

import android.util.Log
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
    val liveDataError: MutableLiveData<CheckInEventoValidation> = MutableLiveData()

    fun tentaCheckIn() {
        CoroutineScope(Dispatchers.Default).launch {
            val dados = liveData.value

            //TODO tratar o !!
            val camposInvalidos = useCase.validaCampos(dados!!)
            if (camposInvalidos != null) {
                liveDataError.postValue(camposInvalidos)
                return@launch
            }

            val objeto = useCase.checkIn(dados)

            Log.v("BMS", "Retorno do checkIn $objeto")
        }
    }

}