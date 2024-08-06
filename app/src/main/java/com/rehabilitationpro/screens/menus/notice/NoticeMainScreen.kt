package com.rehabilitationpro.screens.menus.notice

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen

@Composable
fun NoticeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Notice Screen", style = MaterialTheme.typography.bodyLarge)
        Button(
            onClick = { navController.navigate(Screen.MainMenu.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Back to Main")
        }
    }
}
