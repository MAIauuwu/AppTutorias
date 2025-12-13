package com.example.apptutorias.di

import android.content.Context
import com.example.apptutorias.data.local.AppDatabase
import com.example.apptutorias.data.remote.RetrofitClient
import com.example.apptutorias.data.repository.TutoriaRepository


class AppContainer(context: Context) {


    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    private val tutoriaApiService by lazy {
        RetrofitClient.tutoriaService
    }

    private val weatherApiService by lazy {
        RetrofitClient.weatherService
    }

    val tutoriaRepository: TutoriaRepository by lazy {
        TutoriaRepository(database, tutoriaApiService, weatherApiService)
    }

}