package com.brunomusskopf.listaeventos.remote.checkInEvent.service

import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventRequest
import com.brunomusskopf.listaeventos.domain.checkInEvent.model.CheckInEventResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CheckInApi {

    @POST("checkin")
    fun checkin(@Body request: CheckInEventRequest): Call<CheckInEventResponse?>

}