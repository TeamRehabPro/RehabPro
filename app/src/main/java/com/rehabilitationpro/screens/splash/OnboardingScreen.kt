package com.rehabilitationpro.screens.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.screens.splash.component.StartScreenImage
import com.rehabilitationpro.widgets.GoToSignInScreenButton
import com.rehabilitationpro.widgets.GoToSignUpScreenButton

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 첫 실행 화면 로고 이미지
        StartScreenImage()
        Spacer(modifier = Modifier.height(24.dp))

        // Sign In Screen 전환 Button (로그인 버튼)
        GoToSignInScreenButton(onClick = { navController.navigate(Screen.SignIn.route) })
        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up Screen 전환 Button (회원 가입 버튼)
        GoToSignUpScreenButton(onClick = { navController.navigate(Screen.SignUp.route) })
    }
}
