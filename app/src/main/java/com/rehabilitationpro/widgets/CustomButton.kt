package com.rehabilitationpro.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun SignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enableConditions: Boolean = false
) {
    CustomButton(
        text = "Sign In",
        onClick = onClick,
        modifier = modifier,
        filled = true,
        isButtonEnabled = enableConditions
    )
}

@Composable
fun SignUpButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enableConditions: Boolean = false
) {
    CustomButton(
        text = "Sign Up",
        onClick = onClick,
        modifier = modifier,
        filled = true,
        isButtonEnabled = enableConditions
    )
}

@Composable
fun GoToSignInScreenButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomButton(
        text = "Sign In",
        onClick = onClick,
        modifier = modifier,
        filled = true,
    )
}

@Composable
fun GoToSignUpScreenButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomButton(
        text = "Sign Up",
        onClick = onClick,
        modifier = modifier,
        filled = false,
    )
}

/**
 * A composable function that creates a custom button.
 *
 * @param text The text to be displayed on the button.
 * @param onClick A lambda function to be executed when the button is clicked.
 * @param modifier A Modifier to be applied to the button. Default is Modifier.
 * @param filled Determines if the button should be filled or outlined. True for filled, false for outlined.
 * @param contentColor The color of the button's content (text). Default is White.
 * @param containerColor The background color of the button. Default is ColorPalette.signInBlue.
 * @param isButtonEnabled Determines if the button is enabled. If null, defaults to true (enabled).
 */
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    filled: Boolean = true,
    contentColor: Color = Color.White,
    containerColor: Color = ColorPalette.signInBlue,
    isButtonEnabled: Boolean? = true,
) {
    val enabled = isButtonEnabled ?: true  // Default -> True

    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) {
                if (filled) containerColor else Color.Transparent
            } else {
                ColorPalette.inputBoxGray
            },
            contentColor = if (enabled) {
                if (filled) contentColor else containerColor
            } else {
                Color.White
            },
            disabledContainerColor = ColorPalette.inputBoxGray,  // Disabled -> Button Color
            disabledContentColor = ColorPalette.textGray         // Disabled -> Button Text Color
        ),
        border = if (!filled && enabled) BorderStroke(1.dp, containerColor) else BorderStroke(1.dp, ColorPalette.borderGray),
        enabled = enabled  // 버튼을 활성화/비활성화
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
        )
    }
}
