package com.brunomusskopf.listaeventos.remote.listEvents.service

import com.brunomusskopf.listaeventos.domain.listEvents.model.Event
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsApi {

    @GET("events")
    fun getEvents() : Call<List<Event>?>

    @GET("events/{id}")
    fun getEvent(@Path("id") id: Int) : Call<Event?>
}