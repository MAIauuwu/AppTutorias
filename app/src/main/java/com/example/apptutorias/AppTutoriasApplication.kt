package com.example.apptutorias

import android.app.Application
import com.example.apptutorias.di.AppContainer

class AppTutoriasApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}