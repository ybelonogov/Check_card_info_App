package com.example.testnewapp.data.repository


import com.example.testnewapp.data.model.BinDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BinService {
    @GET("{bin}")
    suspend fun getCardInfo(@Path("bin") binNumber: String): Response<BinDto>


}