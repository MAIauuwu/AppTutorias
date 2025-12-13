package com.example.apptutorias.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TutoriaDao {
    @Query("SELECT * FROM tutorias WHERE estudianteId = :estudianteId")
    fun getTutoriasByEstudiante(estudianteId: Int): Flow<List<TutoriaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tutorias: List<TutoriaEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTutoria(tutoria: TutoriaEntity)
}