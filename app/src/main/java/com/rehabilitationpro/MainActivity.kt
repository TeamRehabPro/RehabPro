// MainActivity.kt
package com.rehabilitationpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rehabilitationpro.screens.MainMenu
import com.rehabilitationpro.screens.authentication.AccountCreationScreen
import com.rehabilitationpro.screens.authentication.LoginScreen
import com.rehabilitationpro.screens.menus.attendance.AttendanceScreen
import com.rehabilitationpro.screens.menus.dashboard.DashboardScreen
import com.rehabilitationpro.screens.menus.messenger.MessengerScreen
import com.rehabilitationpro.screens.menus.notice.NoticeDetailScreen
import com.rehabilitationpro.screens.menus.notice.NoticeMainScreen
import com.rehabilitationpro.screens.menus.notice.notices
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
    NavHost(navController = navController, startDestination = Screen.AuthScreen.Login.route) {
        // Authentication-related screens
        composable(Screen.AuthScreen.Login.route) { LoginScreen(navController) }
        composable(Screen.AuthScreen.AccountCreation.route) { AccountCreationScreen(navController) }

        // Main menu and general screens
        composable(Screen.MainMenu.route) { MainMenu(navController) }

        // Use Notice sealed class for notice screens
        composable(Screen.NoticeScreen.Main.route) { NoticeMainScreen(navController) }
        composable(
            route = Screen.NoticeScreen.Detail.route,
            arguments = listOf(
                navArgument("id") { type = androidx.navigation.NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "No ID"

            // Find the notice with the given ID
            val notice = notices.find { it.id == id }

            // Pass arguments to NoticeDetailScreen
            notice?.let {
                NoticeDetailScreen(
                    navController = navController,
                    title = notice.title,
                    timestamp = notice.timestamp,
                    description = notice.description
                )
            }
        }

        composable(Screen.MenuScreen.Attendance.route) { AttendanceScreen(navController) }
        composable(Screen.MenuScreen.Reservation.route) { ReservationScreen(navController) }
        composable(Screen.MenuScreen.Schedule.route) { ScheduleScreen(navController) }
        composable(Screen.MenuScreen.Dashboard.route) { DashboardScreen(navController) }
        composable(Screen.MenuScreen.Messenger.route) { MessengerScreen(navController) }
    }
}