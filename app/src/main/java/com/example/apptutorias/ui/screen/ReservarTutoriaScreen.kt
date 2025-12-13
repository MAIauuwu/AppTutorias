package com.example.apptutorias.ui.screens.reservar

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.apptutorias.AppTutoriasApplication
import com.example.apptutorias.presentation.viewmodel.ReservarEvent
import com.example.apptutorias.presentation.viewmodel.ReservarViewModel
import com.example.apptutorias.presentation.viewmodel.TutoriaViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservarTutoriaScreen(
    navController: NavController
) {
    // Obtener el Repository del contenedor de dependencias (DI)
    val context = LocalContext.current
    val appContainer = (context.applicationContext as AppTutoriasApplication).container

    // Inyectar el ViewModel usando la factoría
    val viewModel: ReservarViewModel = viewModel(
        factory = TutoriaViewModelFactory(appContainer.tutoriaRepository)
    )

    // Observar el estado de la UI
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reservar Tutoría") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Conexión al Microservicio", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(20.dp))

            // Campo Título
            OutlinedTextField(
                value = state.titulo,
                onValueChange = { viewModel.onEvent(ReservarEvent.TituloChanged(it)) },
                label = { Text("Título de la Tutoría") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Campo Fecha (Simulación)
            OutlinedTextField(
                value = state.fecha,
                onValueChange = { viewModel.onEvent(ReservarEvent.FechaChanged(it)) },
                label = { Text("Fecha (Ej: 2026-03-15)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Envío
            Button(
                onClick = { viewModel.onEvent(ReservarEvent.SubmitReservation) },
                enabled = !state.isLoading,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Enviar Reserva (POST a Backend)")
                }
            }

            // Mensajes de Feedback (Conexión)
            state.errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colorScheme.error)
            }
            state.successMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(it, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}