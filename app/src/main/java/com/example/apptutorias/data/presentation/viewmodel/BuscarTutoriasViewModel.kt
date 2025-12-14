package com.example.apptutorias.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptutorias.data.model.Tutoria
import com.example.apptutorias.data.repository.TutoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class BuscarTutoriasState(
    val tutorias: List<Tutoria> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class BuscarTutoriasViewModel(
    private val repository: TutoriaRepository
) : ViewModel() {

    private val _state = MutableStateFlow(BuscarTutoriasState())
    val state: StateFlow<BuscarTutoriasState> = _state.asStateFlow()

    init {
        fetchTutorias()
    }

    fun fetchTutorias() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            try {
                val result = repository.searchAllTutorias()
                _state.update { it.copy(isLoading = false, tutorias = result) }
            } catch (e: Exception) {
                _state.update { 
                    it.copy(
                        isLoading = false, 
                        errorMessage = "Error al cargar tutorías: ${e.message}"
                    ) 
                }
            }
        }
    }
}
