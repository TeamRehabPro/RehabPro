package com.rehabilitationpro.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen

@Composable
fun AccountCreationScreen(navController: NavHostController) {
    // State variables for input fields
    var userId by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(24.dp))

        // User ID input field
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password input field
        OutlinedTextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Name input field
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Position input field
        OutlinedTextField(
            value = position,
            onValueChange = { position = it },
            label = { Text("Position") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Account Creation Confirmation Button
        Button(
            onClick = {
                // This is where you would handle account creation logic
                // Currently, it's just a placeholder
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Create!")
        }

        // Back to Login Screen Button
        Button(
            onClick = {
                navController.navigate(Screen.AuthScreen.Login.route) {
                    popUpTo(Screen.AuthScreen.AccountCreation.route) { inclusive = true }
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Back to Login")
        }
    }
}
