package com.brunomusskopf.listaeventos.presentation.listEvents.model

import com.brunomusskopf.listaeventos.domain.listEvents.model.Coupon
import com.brunomusskopf.listaeventos.domain.listEvents.model.Person

data class EventView(
    val id: Int?,
    val people: List<Person>?,
    val date: String?,
    val description: String?,
    val image: String?,
    val longitude: Double?,
    val latitude: Double?,
    val price: String?,
    val title: String?,
    val cupons: List<Coupon>?
)