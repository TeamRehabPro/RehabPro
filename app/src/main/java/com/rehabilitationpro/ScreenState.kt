// ScreenState.kt
package com.rehabilitationpro

sealed class Screen(val route: String) {
    // 1 : Authentication Screen
    sealed class AuthScreen(route: String) : Screen(route) {
        data object Login : AuthScreen("login")
        data object SignUp : AuthScreen("signup")
    }

    // 2 : Main menu
    data object MainMenu : Screen("menu")

    // 3 : Notice screens as a part of the Screen hierarchy
    sealed class NoticeScreen(route: String) : Screen(route) {
        data object Main : NoticeScreen("notice")
        data object Detail : NoticeScreen("notice_detail/{id}") {
            fun createRoute(id: String): String {
                return "notice_detail/$id"
            }
        }
    }

    // 4 : Attendance screens as a part of the Screen hierarchy
    sealed class AttendanceScreen(route: String) : Screen(route) {
        data object Main : AttendanceScreen("attendance")
        data object QR : AttendanceScreen("qrcode")
    }

    // 5 : Reservation screens as a part of the Screen hierarchy
    sealed class ReservationScreen(route: String) : Screen(route) {
        data object Main : ReservationScreen("reservation")
    }

    // 6 : Schedule screens as a part of the Screen hierarchy
    sealed class ScheduleScreen(route: String) : Screen(route) {
        data object Main : ScheduleScreen("schedule")
    }

    // 7 : Dashboard screens as a part of the Screen hierarchy
    sealed class DashboardScreen(route: String) : Screen(route) {
        data object Main : DashboardScreen("dashboard")
    }

    // 8 : Messenger screens as a part of the Screen hierarchy
    sealed class MessengerScreen(route: String) : Screen(route) {
        data object Main : MessengerScreen("messenger")
    }
}