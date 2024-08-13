package com.rehabilitationpro.screens.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.screens.signup.component.TermsCheckbox
import com.rehabilitationpro.screens.signup.component.UserRoleDropdown
import com.rehabilitationpro.ui.theme.RehabPROTheme
import com.rehabilitationpro.widgets.EmailInputField
import com.rehabilitationpro.widgets.NameInputField
import com.rehabilitationpro.widgets.PasswordInputField
import com.rehabilitationpro.widgets.PhoneNumberInputField
import com.rehabilitationpro.widgets.SignUpButton
import com.rehabilitationpro.widgets.SignUpScreenHeader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [1] '뒤로 가기' 버튼을 포함한 회원 가입 스크린 헤드
        SignUpScreenHeader(onBackClick = { navController.navigate(Screen.Onboarding.route) })
        Spacer(modifier = Modifier.height(48.dp))

        // [2] 회원 가입 (등록을 위한 필드 입력)
        val userName = remember { mutableStateOf("") }
        NameInputField(fieldValue = userName)
        Spacer(modifier = Modifier.height(16.dp))

        val userId = remember { mutableStateOf("") }
        EmailInputField(fieldValue = userId)
        Spacer(modifier = Modifier.height(16.dp))

        val userPassword = remember { mutableStateOf("") }
        PasswordInputField(fieldValue = userPassword)
        Spacer(modifier = Modifier.height(16.dp))

        val userPhoneNumber = remember { mutableStateOf("") }
        PhoneNumberInputField(fieldValue = userPhoneNumber)
        Spacer(modifier = Modifier.height(16.dp))

        val selectedRole = remember { mutableStateOf<String?>(null) }
        UserRoleDropdown(selectedRole = selectedRole)

        // [3] Terms Check Box
        val isTermsChecked = remember { mutableStateOf(false) }
        TermsCheckbox(isTermsChecked = isTermsChecked)

        // [4] 회원 가입 버튼
        SignUpButton(
            navController = navController,
            userName = userName,
            userId = userId,
            userPassword = userPassword,
            userPhoneNumber = userPhoneNumber,
            selectedRole = selectedRole,
            isTermsChecked = isTermsChecked
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    RehabPROTheme { SignUpScreen(navController = navController) }
}