package com.nrc7.carsalesapp.services

import com.nrc7.carsalesapp.models.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("total")
    fun getWrapperData(
        @Query("date") date: String
    ): Call<Wrapper>
}