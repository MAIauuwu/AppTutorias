package com.example.apptutorias.data.model

data class Tutoria(
    val id: Int? = null,
    val titulo: String,
    val tutorId: Int,
    val estudianteId: Int,
    val fecha: String,
    val estado: String
)