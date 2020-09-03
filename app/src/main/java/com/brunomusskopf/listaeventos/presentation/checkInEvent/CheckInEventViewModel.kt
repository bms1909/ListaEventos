package com.brunomusskopf.listaeventos.presentation.checkInEvent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.checkInEvent.interactor.CheckInEventUseCase
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventValidation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckInEventViewModel(private val useCase: CheckInEventUseCase) : ViewModel() {

    val liveData: MutableLiveData<CheckInEventRequest> = MutableLiveData()
    val liveDataErrorStatus: MutableLiveData<CheckInEventValidation?> = MutableLiveData()
    val liveDataProgress : MutableLiveData<Boolean> = MutableLiveData()

    fun tryCheckIn() {
        CoroutineScope(Dispatchers.Main).launch {
            //com escopo main e setValue, notificação a tela não depende de
            // enfileiramento na main thread
            liveDataProgress.value = true

            //liveData nulo neste ponto deve caracterizar uma exception
            val userInputData = liveData.value!!

            val invalidUserInputObject = useCase.validateFields(userInputData)
            if (invalidUserInputObject != null) {
                liveDataErrorStatus.value = invalidUserInputObject
                liveDataProgress.value = false
                return@launch
            }

            val errorMessage = useCase.sendCheckInOrError(userInputData)

            val errorObject = if (errorMessage == null) {
                null
            } else {
                CheckInEventValidation(null, null, errorMessage)
            }

            liveDataErrorStatus.value = errorObject

            liveDataProgress.value = false
        }
    }
}