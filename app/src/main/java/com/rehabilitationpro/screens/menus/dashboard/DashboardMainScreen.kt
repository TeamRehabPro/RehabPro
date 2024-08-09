package com.rehabilitationpro.screens.menus.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.bar.TopBar

@Composable
fun DashboardMainScreen(navController: NavHostController, drawerState: DrawerState) {
    val currentRoute = Screen.DashboardScreen.Main.route

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
            Text(text = "Dashboard Screen", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
