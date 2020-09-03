package com.brunomusskopf.listaeventos.domain.checkInEvent.model

import com.brunomusskopf.listaeventos.domain.base.StringValidationStatus

class CheckInEventValidation(
    val nameStatus: StringValidationStatus?,
    val emailStatus: StringValidationStatus?,
    val errorMessage : String?
)