package com.example.apptutorias.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://tu-api-backend.com/api/" // Reemplaza con tu URL real
    private const val WEATHER_URL = "https://api.openweathermap.org/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val weatherRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(WEATHER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val tutoriaService: TutoriaApiService by lazy {
        retrofit.create(TutoriaApiService::class.java)
    }

    val weatherService: OpenWeatherService by lazy {
        weatherRetrofit.create(OpenWeatherService::class.java)
    }
}