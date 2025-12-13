package com.example.apptutorias.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptutorias.ui.components.DrawerContent
import com.example.apptutorias.ui.navigation.Routes
import kotlinx.coroutines.launch
import com.example.apptutorias.ui.components.CalendarCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    currentRole: String = "Estudiante" // ⚠️ SIMULACIÓN DE ROL
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContent(navController, scope, drawerState, currentRole)
        },
        content = {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "ClassTutorias",
                                fontWeight = FontWeight.Bold
                            )
                        }, // Nombre de la App
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                Icon(Icons.Filled.Menu, contentDescription = "Menú")
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* Lógica de notificaciones */ }) {
                                Icon(
                                    Icons.Default.Notifications,
                                    contentDescription = "Notificaciones"
                                )
                            }
                        }
                    )
                }
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.15f
                                )
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "Reserva tu tutoría, rápido, seguro y administrado. Toma tu cita aquí con los mejores profesores",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(onClick = { navController.navigate(Routes.Reservar.route) }) {
                                    Text("Tomar Cita Ahora")
                                }
                            }
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Tutorías Destacadas", style = MaterialTheme.typography.titleLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(3) { index ->
                        TutorCard(
                            name = "Profesor Ejemplo $index",
                            onReservarClick = { navController.navigate(Routes.Reservar.route) }
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .height(280.dp)
                                    .padding(end = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        "Disponibilidad",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        "Febrero 2026",
                                        color = MaterialTheme.colorScheme.secondary
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(280.dp)
                                    .padding(start = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        "Recomendaciones",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    RecommendationItem(label = "Asignatura", stars = 5)
                                    RecommendationItem(label = "Hora Inicio", stars = 4)
                                    // Texto pequeño del mockup
                                    Text(
                                        "Los eventos se asignarán a la historial de tutoria que debe demostrarse.",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                    // ui/screens/home/HomeScreen.kt (Fragmento de LazyColumn)

// ...
                    // 4. CALENDARIO y RECOMENDACIONES (Estructura de la derecha en el mockup)
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Columna de Calendario (IMPLEMENTACIÓN FINAL)
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .height(300.dp) // Aumentamos altura para el calendario
                                    .padding(end = 8.dp)
                            ) {
                                CalendarCard()
                            }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp) // Ajustamos altura
                                    .padding(start = 8.dp)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        "Recomendaciones de Estudiantes",
                                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    RecommendationItem(label = "Asignatura", stars = 5)
                                    RecommendationItem(label = "Hora Inicio", stars = 4)
                                    // Texto pequeño del mockup
                                    Text(
                                        "Los eventos se asignarán a la historial de tutoria que debe demostrarse.",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(top = 8.dp)
                                    )
                                }
                            }
                        }
                    }


                    item { Spacer(modifier = Modifier.height(32.dp)) }
                }
            }
        }
    )
}


@Composable
fun TutorCard(name: String, onReservarClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Person,
                contentDescription = "Tutor",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(name, style = MaterialTheme.typography.titleMedium)
                Text("Ingeniería Civil", style = MaterialTheme.typography.bodySmall)
                Row {
                    repeat(5) {
                        Icon(
                            Icons.Default.Star,
                            contentDescription = "Star",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
            Button(onClick = onReservarClick) {
                Text("Reservar")
            }
        }
    }
}

@Composable
fun RecommendationItem(label: String, stars: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.bodyLarge)
            Row {
                repeat(stars) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Detalle")
    }
}
