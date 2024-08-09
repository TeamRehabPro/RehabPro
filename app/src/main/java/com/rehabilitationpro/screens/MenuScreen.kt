// MainMenu.kt
package com.rehabilitationpro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            val menus = listOf(
                Screen.NoticeScreen.Main,
                Screen.AttendanceScreen.Main,
                Screen.ReservationScreen.Main,
                Screen.ScheduleScreen.Main,
                Screen.DashboardScreen.Main,
                Screen.MessengerScreen.Main,
            )
            items(menus) { menu ->
                MenuButton(
                    text = menu.title,
                    route = menu.route,
                    navController = navController
                )
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
