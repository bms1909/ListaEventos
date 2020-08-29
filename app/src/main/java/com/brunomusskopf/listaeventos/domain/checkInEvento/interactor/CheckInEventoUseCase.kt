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
        var nameValidation: StatusValidacaoString? = null
        var emailValidation: StatusValidacaoString? = null

        if (request.name.isNullOrBlank()) {
            nameValidation = StatusValidacaoString.VAZIO
        } else if (request.name!!.length < 3) {
            nameValidation = StatusValidacaoString.INVALIDO
        }

        val regex =
            Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

        if (request.email.isNullOrBlank()) {
            emailValidation = StatusValidacaoString.VAZIO
        } else if (request.email?.matches(regex) != true) {
            emailValidation = StatusValidacaoString.INVALIDO
        }

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