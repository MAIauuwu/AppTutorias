// data/repository/TutoriaRepository.kt
package com.example.apptutorias.data.repository

import com.example.apptutorias.data.local.AppDatabase
import com.example.apptutorias.data.local.TutoriaEntity
import com.example.apptutorias.data.remote.TutoriaApiService
import com.example.apptutorias.data.remote.OpenWeatherService
import com.example.apptutorias.data.remote.model.WeatherResponse
import com.example.apptutorias.data.model.Tutoria
import kotlinx.coroutines.flow.map

class TutoriaRepository(
    private val localDatabase: AppDatabase,
    private val remoteApi: TutoriaApiService,
    private val weatherApi: OpenWeatherService
) {
    private val tutoriaDao = localDatabase.tutoriaDao()

    
    fun getAllTutoriasByEstudiante(estudianteId: Int) = tutoriaDao.getTutoriasByEstudiante(estudianteId).map { localList ->
        localList.map { Tutoria(it.id, it.titulo, it.tutorId, it.estudianteId, it.fecha, it.estado) }
    }

    suspend fun refreshTutorias() {
        try {
            val remoteTutorias = remoteApi.getAllTutorias()

            val entities = remoteTutorias.map {
                TutoriaEntity(
                    id = it.id ?: 0,
                    titulo = it.titulo,
                    tutorId = it.tutorId,
                    estudianteId = it.estudianteId,
                    fecha = it.fecha,
                    estado = it.estado
                )
            }

            tutoriaDao.insertAll(entities)
        } catch (e: Exception) {
            println("Error al refrescar tutorías desde el backend: ${e.message}")
        }
    }

    suspend fun createTutoria(tutoria: Tutoria): Tutoria {
        val newTutoria = remoteApi.createTutoria(tutoria)

        tutoriaDao.insertTutoria(
            TutoriaEntity(
                id = newTutoria.id ?: 0,
                titulo = newTutoria.titulo,
                tutorId = newTutoria.tutorId,
                estudianteId = newTutoria.estudianteId,
                fecha = newTutoria.fecha,
                estado = newTutoria.estado
            )
        )
        return newTutoria
    }

    suspend fun deleteTutoria(id: Int) {
        remoteApi.deleteTutoria(id)
    }

    suspend fun getCurrentWeather(): WeatherResponse {
        return weatherApi.getWeather()
    }

    suspend fun searchAllTutorias(): List<Tutoria> {
        return remoteApi.getAllTutorias()
    }
}