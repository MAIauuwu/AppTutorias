package com.example.apptutorias.data.presentation.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        viewModel = LoginViewModel()
    }

    @Test
    fun `login_with_invalid_email_sets_emailError`() = runTest {
        // GIVEN: Correo y Password no válidos
        viewModel.onEvent(LoginEvent.EmailChanged("a@b"))
        viewModel.onEvent(LoginEvent.PasswordChanged("1234"))

        viewModel.onEvent(LoginEvent.LoginClicked)

        val state = viewModel.state.value
        assertFalse(state.loginSuccess)
        assertTrue(state.emailError != null)
    }

    @Test
    fun `login_with_short_password_sets_passwordError`() = runTest {
        // GIVEN: Email válido y Password muy corto
        viewModel.onEvent(LoginEvent.EmailChanged("test@duoc.cl"))
        viewModel.onEvent(LoginEvent.PasswordChanged("1"))

        // WHEN: Click en Login
        viewModel.onEvent(LoginEvent.LoginClicked)

        // THEN: El estado de error del password debe ser distinto de null
        val state = viewModel.state.value
        assertFalse(state.loginSuccess)
        assertTrue(state.passwordError != null)
    }
}
