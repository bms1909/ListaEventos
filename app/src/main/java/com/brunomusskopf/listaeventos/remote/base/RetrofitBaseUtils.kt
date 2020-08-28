package com.brunomusskopf.listaeventos.remote.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitBaseUtils {

    fun criarRetrofitPadrao() = Retrofit
        .Builder()
        .baseUrl("https://5b840ba5db24a100142dcd8c.mockapi.io/api/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()


    suspend fun <T> executaCall(call: Call<T>): T? =
        withContext(Dispatchers.IO) {
            try {
                call.execute().body()
            } catch (e: Exception) {
                Log.e("BMS", "Erro na comunicação com a API", e)
                null
            }
        }
}