// MainActivity.kt
package com.rehabilitationpro

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.screens.home.HomeScreen
import com.rehabilitationpro.screens.notice.NoticeMainScreen
import com.rehabilitationpro.screens.qrscanner.AttendanceMainScreen
import com.rehabilitationpro.screens.qrscanner.QRScannerScreen
import com.rehabilitationpro.screens.reservation.ReservationMainScreen
import com.rehabilitationpro.screens.schedule.ScheduleMainScreen
import com.rehabilitationpro.screens.setting.EditProfileScreen
import com.rehabilitationpro.screens.setting.SettingScreen
import com.rehabilitationpro.screens.signin.SignInScreen
import com.rehabilitationpro.screens.signup.SignUpScreen
import com.rehabilitationpro.screens.splash.OnboardingScreen
import com.rehabilitationpro.ui.theme.RehabPROTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RehabPROTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.SignIn.route) { SignInScreen(navController) }
        composable(Screen.SignUp.route) { SignUpScreen(navController) }
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.QR.route) { QRScannerScreen(navController) }
        composable(Screen.Setting.route) { SettingScreen(navController) }
        composable(Screen.Profile.route) { EditProfileScreen(navController) }

        // Use Notice sealed class for notice screens
        composable(Screen.NoticeHome.route) { NoticeMainScreen(navController) }
        composable(Screen.Attendance.route) { AttendanceMainScreen(navController) }
        composable(Screen.Reservation.route) { ReservationMainScreen(navController) }
        composable(Screen.Schedule.route) { ScheduleMainScreen(navController) }
    }
}
