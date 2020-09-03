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

    fun createDefaultRetrofit() = Retrofit
        .Builder()
        .baseUrl("https://5b840ba5db24a100142dcd8c.mockapi.io/api/")
        .client(
            OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS).build()
        )
        .addConverterFactory(JacksonConverterFactory.create())
        .build()


    suspend fun <T> executeCall(call: Call<T>): T? =
        withContext(Dispatchers.IO) {
            try {
                val callResult = call.execute()
                var result : T? = null
                if (callResult.isSuccessful) {
                    result = callResult.body()
                }
                result
            } catch (e: Exception) {
                Log.e("BMS", "Erro na comunicação com a API. ${e.message}", e)
                null
            }
        }
}