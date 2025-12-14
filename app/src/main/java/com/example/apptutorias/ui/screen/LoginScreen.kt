package com.example.apptutorias.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // 👈 Necesita la dependencia life-cycle-viewmodel-compose
import androidx.navigation.NavController
import com.example.apptutorias.data.presentation.viewmodel.LoginEvent
import com.example.apptutorias.data.presentation.viewmodel.LoginViewModel
import com.example.apptutorias.ui.navigation.Routes

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.state.collectAsState().value

    if (state.loginSuccess) {
        navController.navigate(Routes.Home.route) {

            popUpTo(Routes.Login.route) { inclusive = true }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) },
                label = { Text("Correo electrónico") },
                isError = state.emailError != null,
                trailingIcon = {
                    if (state.emailError != null) {
                        Icon(Icons.Filled.Warning, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = {
                    state.emailError?.let { Text(it) }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 📝 Campo de Contraseña
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.PasswordChanged(it)) },
                label = { Text("Contraseña") },
                isError = state.passwordError != null,
                trailingIcon = {
                    if (state.passwordError != null) {
                        Icon(Icons.Filled.Warning, "Error", tint = MaterialTheme.colorScheme.error)
                    }
                },
                supportingText = {
                    state.passwordError?.let { Text(it) }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { viewModel.onEvent(LoginEvent.LoginClicked) },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                } else {
                    Text("Ingresar")
                }
            }

            TextButton(
                onClick = { navController.navigate(Routes.Register.route) }
            ) {
                Text("Registrarse")
            }
        }
    }
}
