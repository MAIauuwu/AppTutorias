package com.example.apptutorias.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // ⚠️ 10.0.2.2 es la dirección IP para conectar el emulador con tu localhost
    // Si pruebas en celular físico, cambia esto por tu IP local (ej. 192.168.1.15)
    private const val BASE_URL = "http://10.0.2.2:8080/api/" 
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