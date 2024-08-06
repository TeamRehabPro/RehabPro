package com.rehabilitationpro.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.navigation.Screen

@Composable
fun LoginScreen(navController: NavHostController) {
    // Basic login screen layout
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to RehabPRO")
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // On successful login, navigate to the main menu
                navController.navigate(Screen.MainMenu.route) {
                    // Pop up to the login destination and remove it from back stack
                    popUpTo(Screen.Login.route) { inclusive = true }
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Login")
        }
    }
}
