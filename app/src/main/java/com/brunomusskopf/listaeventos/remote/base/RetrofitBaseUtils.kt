package com.brunomusskopf.listaeventos.remote.base

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBaseUtils {

    fun criarRetrofitPadrao() = Retrofit
        .Builder()
        .baseUrl("https://5b840ba5db24a100142dcd8c.mockapi.io/api/")
        .client(
            OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).build()
        )
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