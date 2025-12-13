package com.example.apptutorias.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object Reservar : Routes("reservar")
    object Perfil : Routes("perfil")
    object Ofrecer : Routes("ofrecer")
    object BuscarTutorias : Routes("buscar")
    object RolSelector : Routes("rol_selector")

    object Calendario : Routes("calendario")
}