package com.brunomusskopf.listaeventos.domain.checkInEvento.interactor

import com.brunomusskopf.listaeventos.domain.base.ValidacaoStringModel
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoValidation
import com.brunomusskopf.listaeventos.domain.checkInEvento.repository.CheckInEventoRepositoryI

class CheckInEventoUseCase(private val repository: CheckInEventoRepositoryI) {

    /**
     * @return se válido retorna nulo, se inválido retorna objeto com detalhes do erro de cada campo
     */
    fun validaCampos(request: CheckInEventoRequest): CheckInEventoValidation? {
        var nameValidation : ValidacaoStringModel? = null
        var emailValidation : ValidacaoStringModel? = null

        if (request.name.isNullOrBlank()) {
            nameValidation = ValidacaoStringModel.VAZIO
        } else if (request.name!!.length < 3) {
            nameValidation = ValidacaoStringModel.INVALIDO
        }

        if (request.email.isNullOrBlank()) {
            emailValidation = ValidacaoStringModel.VAZIO
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