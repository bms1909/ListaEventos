package com.brunomusskopf.listaeventos.domain.checkInEvento.interactor

import com.brunomusskopf.listaeventos.domain.base.StatusValidacaoString
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoValidation
import com.brunomusskopf.listaeventos.domain.checkInEvento.repository.CheckInEventoRepositoryI

class CheckInEventoUseCase(private val repository: CheckInEventoRepositoryI) {

    /**
     * @return se válido retorna nulo, se inválido retorna objeto com detalhes do erro de cada campo
     */
    fun validaCampos(request: CheckInEventoRequest): CheckInEventoValidation? {
        var nameValidation : StatusValidacaoString? = null
        var emailValidation : StatusValidacaoString? = null

        if (request.name.isNullOrBlank()) {
            nameValidation = StatusValidacaoString.VAZIO
        } else if (request.name!!.length < 3) {
            nameValidation = StatusValidacaoString.INVALIDO
        }

        if (request.email.isNullOrBlank()) {
            emailValidation = StatusValidacaoString.VAZIO
        }
        //TODO aprimorar validação de email

        if (nameValidation != null || emailValidation != null) {
            return CheckInEventoValidation(nameValidation, emailValidation, null)
        }

        return null
    }


    /**
     * @return mensagem de erro formatada para exibição ou nulo em caso de sucesso.
     */
    suspend fun executaCheckInOuErro(request: CheckInEventoRequest): String? {
        val resultCode = repository.checkIn(request)?.code

        if (resultCode == "200") {
            return null
        }

        return "Erro de comunicação"
    }
}