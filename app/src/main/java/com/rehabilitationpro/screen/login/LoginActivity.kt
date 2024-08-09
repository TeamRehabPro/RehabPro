package com.rehabilitationpro.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

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
                navController.navigate("main") {
                    // Pop up to the login destination and remove it from back stack
                    popUpTo("login") { inclusive = true }
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Login")
        }
    }
}