package com.example.apptutorias.data.remote

import com.example.apptutorias.data.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double = -33.44, // Latitud de Santiago
        @Query("lon") lon: Double = -70.66, // Longitud de Santiago
        @Query("appid") apiKey: String = "YOUR_API_KEY", // CLAVE DE PRUEBA
        @Query("units") units: String = "metric", // Celsius
        @Query("lang") lang: String = "es"
    ): WeatherResponse
}