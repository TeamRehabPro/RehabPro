package com.rehabilitationpro.widgets

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.network.sendEmployeeRegistration
import com.rehabilitationpro.ui.theme.ColorPalette
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun SignInButton(
    onClick: () -> Unit,
    userId: MutableState<String>,
    userPassword: MutableState<String>,
    modifier: Modifier = Modifier,
    ) {
    val signInConditions = listOf(userId.value, userPassword.value).all { it.isNotEmpty() }

    CustomButton(
        text = "Sign In",
        onClick = onClick,
        modifier = modifier,
        filled = true,
        // 2개의 필드(Id, Password)가 채워지면 버튼 활성화
        isButtonEnabled = signInConditions,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpButton(
    navController: NavController,
    userName: MutableState<String>,
    userId: MutableState<String>,
    userPassword: MutableState<String>,
    userPhoneNumber: MutableState<String>,
    selectedRole: MutableState<String?>,
    isTermsChecked: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

    val signUpConditions =
        userName.value.isNotEmpty() &&
                userId.value.isNotEmpty() &&
                userPassword.value.isNotEmpty() &&
                selectedRole.value != null &&
                isTermsChecked.value
    CustomButton(
        text = "Sign Up",
        onClick = {
            if (signUpConditions) {
                CoroutineScope(Dispatchers.IO).launch {
                    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    sendEmployeeRegistration(
                        employeeName = userName.value,
                        employeeId = userId.value,
                        employeePassword = userPassword.value,
                        employeePhoneNumber = userPhoneNumber.value,
                        position = selectedRole.value ?: "",
                        joiningDate = currentDate
                    ) { result ->
                        result.fold(
                            onSuccess = { responseData ->
                                println("Registration successful: $responseData")
                                CoroutineScope(Dispatchers.Main).launch {
                                    navController.navigate(Screen.SignIn.route)
                                }
                            },
                            onFailure = { error ->
                                println("Registration failed: ${error.message}")
                            }
                        )
                    }
                }
            }
        },
        modifier = modifier,
        filled = true,
        // 5개의 필드(Name, Id, Password, Tole, Term Check)가 채워지면 버튼 활성화
        isButtonEnabled = signUpConditions,
    )
}


@Composable
fun ProfileEditButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomButton(
        text = "Edit Profile",
        onClick = onClick,
        modifier = modifier,
        filled = true,
    )
}

@Composable
fun SettingChangeSaveButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CustomButton(
        text = "Save Changes",
        onClick = onClick,
        modifier = modifier,
        filled = true
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
 * @param iconResId R.drawable.id
 */
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    filled: Boolean = true,
    contentColor: Color = Color.White,
    containerColor: Color = ColorPalette.primaryBlue,
    isButtonEnabled: Boolean? = true,
    iconResId: Int? = null
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
            disabledContainerColor = ColorPalette.inputBoxGray,
            disabledContentColor = ColorPalette.textGray
        ),
        border = if (!filled && enabled) BorderStroke(1.dp, containerColor) else BorderStroke(1.dp, ColorPalette.borderGray),
        enabled = enabled
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (iconResId != null) {
                Image(
                    painter = painterResource(id = iconResId),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 20.sp,
            )
        }
    }
}