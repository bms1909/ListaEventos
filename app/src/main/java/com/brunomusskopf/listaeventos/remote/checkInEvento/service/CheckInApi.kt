package com.brunomusskopf.listaeventos.remote.checkInEvento.service

import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoRequest
import com.brunomusskopf.listaeventos.domain.checkInEvento.model.CheckInEventoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInApi {

    @POST("checkin")
    fun checkin(@Body request: CheckInEventoRequest): Call<CheckInEventoResponse>

}