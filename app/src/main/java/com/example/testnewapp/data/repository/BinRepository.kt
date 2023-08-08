package com.example.testnewapp.data.repository

import android.util.Log
import com.example.testnewapp.common.Constants
import com.example.testnewapp.data.model.BinDto
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BinRepository {
    var cardInfo = MutableStateFlow<BinDto?>(null)

    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader("Accept-Version", "3")
                    .build()
            )
        }
    }

    var mOkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HeaderInterceptor())
        .build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL) // Замените на вашу базовую URL
        .addConverterFactory(GsonConverterFactory.create()).client(mOkHttpClient)
        .build()

    private val binService: BinService = retrofit.create(BinService::class.java)

    suspend fun getInfo(bin: String) {
        try {
            val response = binService.getCardInfo(bin)
            if (response.isSuccessful) {
                cardInfo.value = response.body()!!
            } else {
                throw Exception("Error fetching user data")
            }
        } catch (e: Exception) {
            Log.e("BinRepository", "Error: ${e.message}")
        }
    }
}