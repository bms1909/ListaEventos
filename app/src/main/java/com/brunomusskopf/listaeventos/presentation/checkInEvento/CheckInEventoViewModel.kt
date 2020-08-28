package com.brunomusskopf.listaeventos.presentation.checkInEvento

import androidx.lifecycle.ViewModel
import com.brunomusskopf.listaeventos.domain.checkInEvento.interactor.CheckInEventoUseCase

class CheckInEventoViewModel(private val useCase: CheckInEventoUseCase) : ViewModel()