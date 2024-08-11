package com.rehabilitationpro.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.R
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun NameInputField(
    fieldValue: MutableState<String>,
    modifier: Modifier = Modifier
) {
    CustomInputField(
        fieldValue = fieldValue,
        placeholder = "Enter your name",
        iconResId = R.drawable.icon_user,
        modifier = modifier
    )
}

@Composable
fun EmailInputField(
    fieldValue: MutableState<String>,
    modifier: Modifier = Modifier
) {
    CustomInputField(
        fieldValue = fieldValue,
        placeholder = "Enter your email",
        iconResId = R.drawable.icon_email,
        modifier = modifier
    )
}

@Composable
fun PasswordInputField(
    fieldValue: MutableState<String>,
    modifier: Modifier = Modifier
) {
    CustomInputField(
        fieldValue = fieldValue,
        placeholder = "Enter your password",
        iconResId = R.drawable.icon_lock,
        isPassword = true,
        modifier = modifier
    )
}

/**
 * A customizable input field component for text input.
 *
 * @param fieldValue The mutable state holding the current input value.
 * @param placeholder The placeholder text to display when the input is empty.
 * @param iconResId The icon to display at the start of the input field.
 * @param isPassword Whether this field is for password input. If true, the input will be masked.
 * @param modifier Modifier to be applied to the input field.
 */
@Composable
fun CustomInputField(
    fieldValue: MutableState<String>,
    placeholder: String,
    iconResId: Int,
    isPassword: Boolean = false,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val borderColor = if (fieldValue.value.isEmpty()) ColorPalette.borderGray else ColorPalette.signInBlue

    Surface(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        color = ColorPalette.inputBoxGray,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = ColorPalette.textGray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = fieldValue.value,
                onValueChange = {
                    fieldValue.value = it
                },
                textStyle = TextStyle(color = Color.Black),
                decorationBox = { innerTextField ->
                    Box {
                        if (fieldValue.value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = ColorPalette.textGray
                            )
                        }
                        innerTextField()
                    }
                },
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = if (isPassword) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
