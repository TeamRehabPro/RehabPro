package com.rehabilitationpro.screens.menus.attendance

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.navigation.Screen

@Composable
fun AttendanceScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Attendance Screen", style = MaterialTheme.typography.bodyLarge)
        Button(
            onClick = { navController.navigate(Screen.MainMenu.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Back to Main")
        }
    }
}
