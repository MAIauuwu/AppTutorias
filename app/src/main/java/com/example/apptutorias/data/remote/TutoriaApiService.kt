package com.example.apptutorias.data.remote

import com.example.apptutorias.data.model.Tutoria
import retrofit2.http.*

interface TutoriaApiService {
    @GET("tutorias/all")
    suspend fun getAllTutorias(): List<Tutoria>

    @POST("tutorias")
    suspend fun createTutoria(@Body tutoria: Tutoria): Tutoria

    @DELETE("tutorias/{id}")
    suspend fun deleteTutoria(@Path("id") id: Int)
}