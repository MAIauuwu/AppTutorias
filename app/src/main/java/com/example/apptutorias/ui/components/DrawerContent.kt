package com.example.apptutorias.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptutorias.ui.navigation.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class DrawerItem(val icon: ImageVector, val label: String, val route: String)

@Composable
fun DrawerContent(navController: NavController, scope: CoroutineScope, drawerState: DrawerState, currentRole: String) {
    val userName = if (currentRole == "Usuario Invitado") "Invitado" else "Ana Pérez"
    val userRole = currentRole

    val menuItems = when (currentRole) {
        "Profesor" -> listOf(
            DrawerItem(Icons.Default.Add, "Ofrecer Tutoría", Routes.Ofrecer.route),
            DrawerItem(Icons.AutoMirrored.Filled.List, "Mis Tutorías", Routes.Home.route),
            DrawerItem(Icons.Default.DateRange, "Reservas", Routes.Calendario.route),
            DrawerItem(Icons.Default.Person, "Perfil", Routes.Perfil.route)
        )
        else -> listOf( // Estudiante, Administrador, Invitado
            DrawerItem(Icons.Default.Search, "Buscar Tutorías", Routes.BuscarTutorias.route),
            DrawerItem(Icons.Default.Schedule, "Mis Reservas", Routes.Home.route),
            DrawerItem(Icons.Default.CalendarToday, "Calendario", Routes.Calendario.route),
            DrawerItem(Icons.Default.Person, "Perfil", Routes.Perfil.route)
        )
    }

    ModalDrawerSheet(
        drawerContainerColor = Color(0xFFE57373), // Color rojizo pastel solicitado
        drawerContentColor = Color.White, // Texto blanco para contraste
        modifier = Modifier.width(300.dp)
    ) {
        // HEADER DEL DRAWER
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                imageVector = Icons.Default.Person,
                contentDescription = "Foto de Perfil",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(userName, style = MaterialTheme.typography.titleLarge, color = Color.White)
            Text(userRole, style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.8f))
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color.White.copy(alpha = 0.5f))

        // ITEMS DEL MENÚ
        menuItems.forEach { item ->
            NavigationDrawerItem(
                icon = { Icon(item.icon, contentDescription = item.label, tint = Color.White) },
                label = { Text(item.label, color = Color.White) },
                selected = item.route == navController.currentDestination?.route,
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color.White.copy(alpha = 0.2f),
                    unselectedContainerColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(item.route)
                    scope.launch { drawerState.close() }
                },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Empuja el cierre de sesión al fondo

        NavigationDrawerItem(
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar Sesión", tint = Color.White) },
            label = { Text("Cerrar Sesión", color = Color.White) },
            selected = false,
            colors = NavigationDrawerItemDefaults.colors(
                unselectedContainerColor = Color.Transparent
            ),
            onClick = {
                navController.navigate(Routes.Login.route) {
                    popUpTo(Routes.Home.route) { inclusive = true } // Limpia el stack
                }
                scope.launch { drawerState.close() }
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}
