package com.rehabilitationpro.screens.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.rehabilitationpro.screens.signin.component.FindPasswordButton
import com.rehabilitationpro.screens.signin.component.OrDivider
import com.rehabilitationpro.screens.signin.component.SignUpPrompt
import com.rehabilitationpro.ui.theme.RehabPROTheme
import com.rehabilitationpro.widgets.EmailInputField
import com.rehabilitationpro.widgets.PasswordInputField
import com.rehabilitationpro.widgets.SignInButton
import com.rehabilitationpro.widgets.SignInScreenHeader
import com.rehabilitationpro.widgets.SignInWithFacebook
import com.rehabilitationpro.widgets.SignInWithGoogle

@Composable
fun SignInScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [1] '뒤로 가기' 버튼을 포함한 로그인 스크린 헤드
        SignInScreenHeader(onBackClick = { navController.navigate(Screen.Onboarding.route) })
        Spacer(modifier = Modifier.height(64.dp))

        // [2] 로그인 용 이메일 / 비밀 번호 입력 필드
        val userId = remember { mutableStateOf("") }
        EmailInputField(fieldValue = userId)
        Spacer(modifier = Modifier.height(16.dp))

        val userPassword = remember { mutableStateOf("") }
        PasswordInputField(fieldValue = userPassword)

        // [3] 비밀 번호 찾기 버튼
        FindPasswordButton(modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End))
        Spacer(modifier = Modifier.height(48.dp))

        // [4] 로그인 버튼
        SignInButton(
            onClick = { navController.navigate(Screen.Home.route) },
            userId = userId,
            userPassword = userPassword,
        )
        Spacer(modifier = Modifier.height(8.dp))

        // [5] 계정이 없다면 -> 회원 가입 버튼
        SignUpPrompt(onSignUpClick = { navController.navigate(Screen.SignUp.route) })
        Spacer(modifier = Modifier.height(48.dp))

        // [6] ---- OR ----
        OrDivider()
        Spacer(modifier = Modifier.height(24.dp))

        // [7] 소셜 로그인
        SignInWithGoogle()
        Spacer(modifier = Modifier.height(16.dp))
        SignInWithFacebook()
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    val navController = rememberNavController()
    RehabPROTheme { SignInScreen(navController = navController) }
}