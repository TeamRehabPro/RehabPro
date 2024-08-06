package com.rehabilitationpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.screens.MainMenu
import com.rehabilitationpro.screens.account.AccountCreationScreen
import com.rehabilitationpro.screens.menus.attendance.AttendanceScreen
import com.rehabilitationpro.screens.menus.dashboard.DashboardScreen
import com.rehabilitationpro.screens.login.LoginScreen
import com.rehabilitationpro.screens.menus.messenger.MessengerScreen
import com.rehabilitationpro.screens.menus.notice.NoticeScreen
import com.rehabilitationpro.screens.menus.reservation.ReservationScreen
import com.rehabilitationpro.screens.menus.schedule.ScheduleScreen
import com.rehabilitationpro.ui.theme.RehabPROTheme

class MainActivity : ComponentActivity() {
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

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) { LoginScreen(navController) }
        composable(Screen.MainMenu.route) { MainMenu(navController) }
        composable(Screen.Notice.route) { NoticeScreen(navController) }
        composable(Screen.Attendance.route) { AttendanceScreen(navController) }
        composable(Screen.Reservation.route) { ReservationScreen(navController) }
        composable(Screen.Schedule.route) { ScheduleScreen(navController) }
        composable(Screen.Dashboard.route) { DashboardScreen(navController) }
        composable(Screen.Messenger.route) { MessengerScreen(navController) }
        composable(Screen.AccountCreation.route) { AccountCreationScreen(navController) }
    }
}