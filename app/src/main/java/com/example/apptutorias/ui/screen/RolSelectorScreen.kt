package com.example.apptutorias.ui.screens.rolselector

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptutorias.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RolSelectorScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    val roles = listOf("Estudiante", "Profesor", "Administrador", "Usuario Invitado")
    var selectedRole by remember { mutableStateOf(roles[0]) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selecciona tu Rol",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Exposed Dropdown Menu
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedRole,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Rol") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    roles.forEach { role ->
                        DropdownMenuItem(
                            text = { Text(role) },
                            onClick = {
                                selectedRole = role
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (selectedRole == "Usuario Invitado") {
                        navController.navigate(Routes.BuscarTutorias.route)
                    } else {
                        navController.navigate(Routes.Login.route)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (selectedRole == "Usuario Invitado") "Continuar como Invitado" else "Ir a Iniciar Sesión")
            }
        }
    }
}