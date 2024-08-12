package com.rehabilitationpro.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun EmailInputField(
    state: MutableState<String>,
    modifier: Modifier = Modifier
) {
    CustomInputField(
        state = state,
        placeholder = "Enter your email",
        icon = Icons.Default.Email,
        modifier = modifier
    )
}

@Composable
fun PasswordInputField(
    state: MutableState<String>,
    modifier: Modifier = Modifier
) {
    CustomInputField(
        state = state,
        placeholder = "Enter your password",
        icon = Icons.Default.Lock,
        isPassword = true,
        modifier = modifier
    )
}

/**
 * A customizable input field component for text input.
 *
 * @param state The mutable state holding the current input value.
 * @param placeholder The placeholder text to display when the input is empty.
 * @param icon The icon to display at the start of the input field.
 * @param isPassword Whether this field is for password input. If true, the input will be masked.
 * @param modifier Modifier to be applied to the input field.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomInputField(
    state: MutableState<String>,
    placeholder: String,
    icon: ImageVector,
    isPassword: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = ColorPalette.inputBoxGray)
            .fillMaxWidth(0.8f)
            .height(56.dp)
            .background(Color.White)
            .border(1.dp, ColorPalette.borderGray, shape = RoundedCornerShape(12.dp))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ColorPalette.textGray,
            modifier = Modifier
                .padding(start = 24.dp, end = 8.dp)
                .fillMaxHeight()
        )
        TextField(
            value = state.value,
            onValueChange = { state.value = it },
            placeholder = {
                Text(
                    text = placeholder,
                    color = ColorPalette.textGray
                )
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                cursorColor = ColorPalette.signInBlue,
            ),
        )
    }
}