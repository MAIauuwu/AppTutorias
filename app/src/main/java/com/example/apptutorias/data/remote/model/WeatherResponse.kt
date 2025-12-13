package com.example.apptutorias.data.remote.model

data class WeatherResponse(
    val main: WeatherMain,
    val weather: List<WeatherDescription>,
    val name: String
)

data class WeatherMain(
    val temp: Double,
    val temp_min: Double,
    val temp_max: Double
)

data class WeatherDescription(
    val description: String,
    val icon: String
)