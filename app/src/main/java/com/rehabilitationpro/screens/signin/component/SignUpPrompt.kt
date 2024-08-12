package com.rehabilitationpro.screens.signin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun SignUpPrompt(onSignUpClick: () -> Unit) {
    Row {
        Text(text = "Don't have an account?")
        Text(
            text = " Sign Up",
            color = ColorPalette.primaryBlue,
            modifier = Modifier.clickable(onClick = onSignUpClick)
        )
    }
}