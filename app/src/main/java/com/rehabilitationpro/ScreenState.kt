// ScreenState.kt
package com.rehabilitationpro

sealed class Screen(val route: String, val title: String = "") {
    data object Onboarding : Screen("onboard")
    data object SignIn : Screen("sign_in")
    data object SignUp : Screen("sign_up")
    data object Home : Screen("home")
    data object QR : Screen("qrcode")
    data object Setting : Screen("setting")

    data object NoticeHome : Screen("notice_home")
    data object NoticeDetail : Screen("notice_detail/{id}") {
        fun createRoute(id: String): String {
            return "notice_detail/$id"
        }
    }
    data object Attendance : Screen("attendance")
    data object Reservation : Screen("reservation")
    data object Schedule : Screen("schedule")
}