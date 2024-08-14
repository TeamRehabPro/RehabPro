// ScreenState.kt
package com.rehabilitationpro

sealed class Screen(val route: String) {
    data object Onboarding : Screen("onboard")
    data object SignIn : Screen("sign_in")
    data object SignUp : Screen("sign_up")
    data object Home : Screen("home")
    data object QR : Screen("qrcode")
    data object Setting : Screen("setting")
    data object Profile : Screen("profile")

    data object NoticeHome : Screen("notice_home")
    data object Reservation : Screen("reservation")
    data object Schedule : Screen("schedule")
}