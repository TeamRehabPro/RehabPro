package com.rehabilitationpro

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object MainMenu : Screen("menu")
    data object Notice : Screen("notice")
    data object Attendance : Screen("attendance")
    data object Reservation : Screen("reservation")
    data object Schedule : Screen("schedule")
    data object Dashboard : Screen("dashboard")
    data object Messenger : Screen("messenger")
    data object AccountCreation : Screen("account")
}
