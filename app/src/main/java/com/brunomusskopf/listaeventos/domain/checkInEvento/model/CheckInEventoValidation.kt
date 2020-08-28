package com.brunomusskopf.listaeventos.domain.checkInEvento.model

import com.brunomusskopf.listaeventos.domain.base.StatusValidacaoString

class CheckInEventoValidation(
    val nameStatus: StatusValidacaoString?,
    val emailStatus: StatusValidacaoString?,
    val errorMessage : String?
)