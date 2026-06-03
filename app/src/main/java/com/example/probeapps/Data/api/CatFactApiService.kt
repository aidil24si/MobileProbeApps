package com.example.probeapps.Data.api

import com.example.probeapps.Data.model.CatFactModel
import retrofit2.http.GET

interface CatFactApiService {
    @GET("list")
    suspend fun getCatFact(): CatFactModel
}