package com.example.apptutorias.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptutorias.ui.screens.*
import com.example.apptutorias.ui.screens.home.HomeScreen
import com.example.apptutorias.ui.screens.rolselector.RolSelectorScreen
import com.example.apptutorias.ui.screens.CalendarioScreen
@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.RolSelector.route
    ) {
        composable(Routes.RolSelector.route) { RolSelectorScreen(navController) }
        composable(Routes.Login.route) { LoginScreen(navController) }
        composable(Routes.Register.route) { RegisterScreen(navController) }
        composable(Routes.Home.route) { HomeScreen(navController) }
        composable(Routes.Reservar.route) { ReservarTutoriaScreen(navController) }
        composable(Routes.Perfil.route) { PerfilScreen(navController) }
        composable(Routes.Ofrecer.route) { OfrecerTutoriaScreen(navController) }
        composable(Routes.BuscarTutorias.route) { BuscarTutoriasScreen(navController) }
        composable(Routes.Calendario.route) { CalendarioScreen(navController) }
    }
}
