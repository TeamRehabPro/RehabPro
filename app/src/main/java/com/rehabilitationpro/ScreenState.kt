package com.rehabilitationpro

// Base sealed class for all screens
sealed class Screen(val route: String) {
    // Authentication-related screens
    sealed class AuthScreen(route: String) : Screen(route) {
        data object Login : AuthScreen("login")
        data object AccountCreation : AuthScreen("account")
    }

    // Main menu and general screens
    data object MainMenu : Screen("menu")

    // Notice screens as a part of the Screen hierarchy
    sealed class NoticeScreen(route: String) : Screen(route) {
        data object Main : NoticeScreen("notice")
        data object Detail : NoticeScreen("notice_detail/{id}") {
            fun createRoute(id: String): String {
                return "notice_detail/$id"
            }
        }
    }

    // Subcategory for menu screens
    sealed class MenuScreen(route: String) : Screen(route) {
        data object Attendance : MenuScreen("attendance")
        data object Reservation : MenuScreen("reservation")
        data object Schedule : MenuScreen("schedule")
        data object Dashboard : MenuScreen("dashboard")
        data object Messenger : MenuScreen("messenger")
    }
}