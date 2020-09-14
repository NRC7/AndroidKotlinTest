package com.nrc7.carsalesapp.services

import com.nrc7.carsalesapp.models.Wrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Request Handler
interface ApiService {

    // Get a object from a giving date
    // https://covid-19-statistics.p.rapidapi.com/reports/total?date=2020-04-07
    @GET("total")
    fun getWrapperData(
        @Query("date") date: String
    ): Call<Wrapper>

    // https://api.edamam.com/api/
    // nutrition-data?app_id=62d6bc9b&app_key=48fd19a9a48cdd81ea1aa421bb56efc7&ingr=
    // string hola = 1%20large%20
    // string usuario = chicken
    // hola + usuario
    @GET("nutrition-data")
    fun obtenerNutritientes(
        @Query("app_id") app_id: String,
        @Query("app_key") app_key: String,
        @Query("ingr") ingr: String
    ): Call<Wrapper>
}