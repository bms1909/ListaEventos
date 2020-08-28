package com.brunomusskopf.listaeventos.domain.checkInEvento.model

import com.brunomusskopf.listaeventos.domain.base.ValidacaoStringModel

class CheckInEventoValidation(
    val nameStatus: ValidacaoStringModel?,
    val emailStatus: ValidacaoStringModel?,
    val errorMessage : String?
)