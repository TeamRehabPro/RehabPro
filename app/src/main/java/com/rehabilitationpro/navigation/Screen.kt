package com.rehabilitationpro.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object MainMenu : Screen("menu")
    object Notice : Screen("notice")
    object Attendance : Screen("attendance")
    object Reservation : Screen("reservation")
    object Schedule : Screen("schedule")
    object Dashboard : Screen("dashboard")
    object Messenger : Screen("messenger")
}
