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

data class ReservarState(
    val titulo: String = "",
    val fecha: String = "",
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)

sealed class ReservarEvent {
    data class TituloChanged(val titulo: String) : ReservarEvent()
    data class FechaChanged(val fecha: String) : ReservarEvent()
    object SubmitReservation : ReservarEvent()
}

class ReservarViewModel(
    private val repository: TutoriaRepository // 👈 El Repository inyectado
) : ViewModel() {

    private val _state = MutableStateFlow(ReservarState())
    val state: StateFlow<ReservarState> = _state.asStateFlow()

    fun onEvent(event: ReservarEvent) {
        when (event) {
            is ReservarEvent.TituloChanged -> _state.update { it.copy(titulo = event.titulo, errorMessage = null) }
            is ReservarEvent.FechaChanged -> _state.update { it.copy(fecha = event.fecha, errorMessage = null) }
            ReservarEvent.SubmitReservation -> submitReservation()
        }
    }

    private fun submitReservation() {
        // Validación básica (debería ser más robusta)
        if (_state.value.titulo.isBlank() || _state.value.fecha.isBlank()) {
            _state.update { it.copy(errorMessage = "Todos los campos son obligatorios.") }
            return
        }

        _state.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            try {
                val newTutoria = repository.createTutoria(
                    Tutoria(
                        titulo = _state.value.titulo,
                        tutorId = 1, // IDs de ejemplo
                        estudianteId = 2,
                        fecha = _state.value.fecha,
                        estado = "Pendiente"
                    )
                )

                _state.update {
                    it.copy(
                        isLoading = false,
                        successMessage = "¡Tutoría '${newTutoria.titulo}' creada exitosamente! (ID: ${newTutoria.id})",
                        titulo = "", // Limpiar formulario
                        fecha = ""
                    )
                }

            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error de conexión: No se pudo crear la tutoría. Revise el backend. (${e.message})"
                    )
                }
            }
        }
    }
}