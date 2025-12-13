// presentation/viewmodel/ViewModelFactory.kt
package com.example.apptutorias.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apptutorias.data.repository.TutoriaRepository

class TutoriaViewModelFactory(
    private val repository: TutoriaRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservarViewModel::class.java)) {
            return ReservarViewModel(repository) as T
        }


        throw IllegalArgumentException("Unknown ViewModel class")
    }
}