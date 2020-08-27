package com.brunomusskopf.listaeventos.remote.listaEventos.dataSourceImpl

import android.util.Log
import com.brunomusskopf.listaeventos.data.listaEventos.dataSource.EventosRemoteDataSourceI
import com.brunomusskopf.listaeventos.domain.listaEventos.model.Evento
import com.brunomusskopf.listaeventos.remote.listaEventos.service.EventosApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class EventosRemoteDataSourceImpl(private val api : EventosApi) : EventosRemoteDataSourceI {

    override suspend fun buscaEventos(): List<Evento>? =
        withContext(Dispatchers.IO) {
            try {
                api.getEventos()
                    .execute()
                    .body()
            } catch (e : Exception) {
                Log.e("BMS", "Erro na comunicação com a API", e)
                null
            }
        }

    override suspend fun buscaEvento(id: Int): Evento? = api.getEvento(id).execute().body()
}