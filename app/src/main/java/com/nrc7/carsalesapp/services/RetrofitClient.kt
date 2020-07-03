package com.nrc7.carsalesapp.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Base Url
    val BASE_URL = "https://covid-19-statistics.p.rapidapi.com/reports/"

    // Key Headers
    val KEY = "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e"

    // Add interceptor to include auth headers
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{ chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
            .addHeader("X-RapidAPI-Key", KEY)
            .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }

    // Instance builder
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()

        retrofit.create(ApiService::class.java)
    }
}