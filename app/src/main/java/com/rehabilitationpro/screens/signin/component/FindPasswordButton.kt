package com.rehabilitationpro.screens.signin.component

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun FindPasswordButton(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = { /*TODO : Find user's password*/
            Toast.makeText(context, "Find user's password : TO BE UPDATED", Toast.LENGTH_SHORT).show()
        },
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = modifier
    ) {
        Text(
            text = "Forgot password?",
            fontWeight = FontWeight.W400,
            color = ColorPalette.signInBlue
        )
    }
}