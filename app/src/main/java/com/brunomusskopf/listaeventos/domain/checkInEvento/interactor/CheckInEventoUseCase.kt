package com.brunomusskopf.listaeventos.domain.checkInEvento.interactor

import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoValidation
import com.brunomusskopf.listaeventos.domain.checkInEvento.repository.CheckInEventoRepositoryI

class CheckInEventoUseCase(private val repository: CheckInEventoRepositoryI) {

    fun validaCampos(request: CheckInEventoRequest): CheckInEventoValidation? {
        //TODO implementar validação
        return null
    }


    suspend fun checkIn(request: CheckInEventoRequest): String? {
        val resultCode = repository.checkIn(request)?.code

        if (resultCode == "200") {
            return null
        }

        return "Erro de comunicação"
    }

}