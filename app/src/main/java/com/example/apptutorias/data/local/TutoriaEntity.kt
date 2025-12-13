package com.example.apptutorias.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tutorias")
data class TutoriaEntity(
    @PrimaryKey val id: Int,
    val titulo: String,
    val tutorId: Int,
    val estudianteId: Int,
    val fecha: String,
    val estado: String
)