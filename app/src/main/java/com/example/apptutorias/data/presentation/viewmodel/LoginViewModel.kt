package com.example.apptutorias.data.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptutorias.data.model.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoginState())

    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.update { it.copy(email = event.email, emailError = null) }
            }
            is LoginEvent.PasswordChanged -> {
                _state.update { it.copy(password = event.password, passwordError = null) }
            }
            LoginEvent.LoginClicked -> {
                validateAndLogin()
            }
        }
    }

    private fun validateAndLogin() {
        if (!validateFields()) return

        _state.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            kotlinx.coroutines.delay(2000) // Simular espera de 2 segundos

            if (_state.value.email == "test@duoc.cl" && _state.value.password == "1234") {
                _state.update { it.copy(isLoading = false, loginSuccess = true) }
            } else {
                _state.update { it.copy(isLoading = false, emailError = "Credenciales inválidas") }
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true
        val currentEmail = _state.value.email
        val currentPassword = _state.value.password

        // Validar Email (ej. que contenga un '@')
        if (!currentEmail.contains("@") || currentEmail.length < 5) {
            _state.update { it.copy(emailError = "El email no es válido o es muy corto") }
            isValid = false
        }

        // Validar Password (ej. mínimo 8 caracteres)
        if (currentPassword.length < 8) {
            _state.update { it.copy(passwordError = "La contraseña debe tener al menos 8 caracteres") }
            isValid = false
        }
        return isValid
    }
}

sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object LoginClicked : LoginEvent()
}