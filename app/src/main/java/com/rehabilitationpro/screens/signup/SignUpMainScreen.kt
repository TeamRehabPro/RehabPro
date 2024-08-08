// File: com/rehabilitationpro/screens/signup/AccountCreationScreen.kt

package com.rehabilitationpro.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.screens.signup.component.IdInputField
import com.rehabilitationpro.screens.signup.component.NameInputField
import com.rehabilitationpro.screens.signup.component.PasswordInputField
import com.rehabilitationpro.screens.signup.component.PositionDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountCreationScreen(navController: NavHostController) {
    // State variables for input fields
    var employeeId by remember { mutableStateOf("") }
    var employeePassword by remember { mutableStateOf("") }
    var employeeName by remember { mutableStateOf("") }
    var selectedPosition by remember { mutableStateOf("") }

    // State variables for error messages
    var idError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var positionError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.AuthScreen.Login.route)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
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
        IdInputField(
            userId = employeeId,
            onUserIdChange = { employeeId = it },
            errorMessage = idError
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password input field
        PasswordInputField(
            userPassword = employeePassword,
            onUserPasswordChange = { employeePassword = it },
            errorMessage = passwordError
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Name input field
        NameInputField(
            name = employeeName,
            onNameChange = { employeeName = it },
            errorMessage = nameError
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Position Dropdown
        PositionDropdown(
            selectedPosition = selectedPosition,
            onPositionSelected = { selectedPosition = it },
            errorMessage = positionError
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Back to Login Screen Button
        Button(
            onClick = {
                // Validate fields
                idError = if (employeeId.isEmpty()) "ID cannot be empty" else null
                passwordError = if (employeePassword.isEmpty()) "Password cannot be empty" else null
                nameError = if (employeeName.isEmpty()) "Name cannot be empty" else null
                positionError = if (selectedPosition.isEmpty()) "Position must be selected" else null

                // Check if all fields are valid
                if (idError == null && passwordError == null && nameError == null && positionError == null) {
                    // Print the field values for debugging purposes
                    println("ID: $employeeId")
                    println("Password: $employeePassword")
                    println("Name: $employeeName")
                    println("Position: $selectedPosition")

                    // Proceed with account creation logic
                    navController.navigate(Screen.AuthScreen.Login.route) {
                        popUpTo(Screen.AuthScreen.SignUp.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Create!")
        }
    }
}
    }
