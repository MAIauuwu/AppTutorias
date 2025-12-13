package com.example.tutoriaapp.ui.navigation

sealed class Routes(val route: String) {
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object ReservarTutoria : Routes("reservar")
    object Perfil : Routes("perfil")
}
