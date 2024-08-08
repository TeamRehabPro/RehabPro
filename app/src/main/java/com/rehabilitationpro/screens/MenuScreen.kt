// MainMenu.kt
package com.rehabilitationpro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.bar.TopBar
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun MainMenu(navController: NavHostController, drawerState: DrawerState) {
    val currentRoute = Screen.MainMenu.route

    Scaffold(
        topBar = { TopBar(navController, drawerState, currentRoute) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Create a grid layout for the buttons
            val buttons = listOf(
                Pair("공지사항", Screen.NoticeScreen.Main.route),
                Pair("근태", Screen.AttendanceScreen.Main.route),
                Pair("환자예약", Screen.ReservationScreen.Main.route),
                Pair("병원일정", Screen.ScheduleScreen.Main.route),
                Pair("자재관리", Screen.DashboardScreen.Main.route),
                Pair("사내메신저", Screen.MessengerScreen.Main.route)
            )

            for (row in 0 until 3) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (col in 0 until 2) {
                        val index = row * 2 + col
                        if (index < buttons.size) {
                            MenuButton(
                                text = buttons[index].first,
                                route = buttons[index].second,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuButton(text: String, route: String, navController: NavHostController) {
    val colors = ColorPalette()
    Box(
        modifier = Modifier
            .size(150.dp)
            .background(colors.mySkyBlue)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { navController.navigate(route) },
            modifier = Modifier.fillMaxSize(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(0.dp)
        ) {
            Text(text = text)
        }
    }
}
