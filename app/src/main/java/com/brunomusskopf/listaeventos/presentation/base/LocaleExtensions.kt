package com.brunomusskopf.listaeventos.presentation.base

import java.text.SimpleDateFormat
import java.util.*


fun Long.toDefaultStringDate(): String {
    val formater = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = Date(this)
    return formater.format(date)
}