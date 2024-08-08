package com.rehabilitationpro.screens.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordInputField(
    userPassword: String,
    onUserPasswordChange: (String) -> Unit,
    errorMessage: String? = null // Optional error message parameter
) {
    OutlinedTextField(
        value = userPassword,
        onValueChange = { onUserPasswordChange(it) },
        label = { Text("Password") },
        isError = errorMessage != null, // Set error state if there is an error message
        supportingText = { errorMessage?.let { Text(it) } }, // Show error message if present
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}
