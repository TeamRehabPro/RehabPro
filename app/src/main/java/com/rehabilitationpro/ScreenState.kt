// ScreenState.kt
package com.rehabilitationpro

sealed class Screen(val route: String, val title: String = "") {
    data object Onboarding : Screen("onboard", "첫 화면")

    data object SignIn : Screen("sign_in", "로그인")


    // 1 : Authentication Screen
    sealed class AuthScreen(route: String, title: String) : Screen(route, title) {
        data object Login : AuthScreen("login", "로그인")
        data object SignUp : AuthScreen("signup", "회원가입")
    }

    // 2 : Main menu
    data object MainMenu : Screen("menu", "메뉴")

    // 3 : Notice screens as a part of the Screen hierarchy
    sealed class NoticeScreen(route: String, title: String = "") : Screen(route, title) {
        data object Main : NoticeScreen("notice", "공지사항")
        data object Detail : NoticeScreen("notice_detail/{id}") {
            fun createRoute(id: String): String {
                return "notice_detail/$id"
            }
        }
    }

    // 4 : Attendance screens as a part of the Screen hierarchy
    sealed class AttendanceScreen(route: String, title: String = "") : Screen(route, title) {
        data object Main : AttendanceScreen("attendance", "근태")
        data object QR : AttendanceScreen("qrcode")
    }

    // 5 : Reservation screens as a part of the Screen hierarchy
    sealed class ReservationScreen(route: String, title: String = "") : Screen(route, title) {
        data object Main : ReservationScreen("reservation", "환자예약")
    }

    // 6 : Schedule screens as a part of the Screen hierarchy
    sealed class ScheduleScreen(route: String, title: String = "") : Screen(route, title) {
        data object Main : ScheduleScreen("schedule", "병원일정")
    }

    // 7 : Dashboard screens as a part of the Screen hierarchy
    sealed class DashboardScreen(route: String, title: String = "") : Screen(route, title) {
        data object Main : DashboardScreen("dashboard", "자재관리")
    }

    // 8 : Messenger screens as a part of the Screen hierarchy
    sealed class MessengerScreen(route: String, title: String = "") : Screen(route, title) {
        data object Main : MessengerScreen("messenger", "사내메신저")
    }
}