package com.brunomusskopf.listaeventos.inject

import org.koin.core.module.Module

object KoinManager {

    fun getModulosAplicacao() = listOf<Module>()
        .plus(ListaEventosKoinModule.getModulosListaEventos())
        .plus(CheckInEventoKoinModule.getModulosCheckInEvento())
}


