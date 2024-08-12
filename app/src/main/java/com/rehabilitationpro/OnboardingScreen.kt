package com.rehabilitationpro

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 첫 실행 화면 로고 이미지
        Image(
            painter = painterResource(id = R.drawable.doctor), // 이미지 경로 : res/drawable 에서 불러올 수 있음.
            modifier = Modifier
                .fillMaxHeight(0.64f),  // 이미지 전체 크기의 64%
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
        )

        // Sign In Button (로그인 버튼)
        Button(
            onClick = { navController.navigate(Screen.AuthScreen.Login.route) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = ColorPalette.signInBlue
            ),
        ) {
            Text(text = "Sign In", color = Color.White, fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Sign Up Button (회원 가입 버튼)
        Button(
            onClick = { navController.navigate(Screen.AuthScreen.SignUp.route) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            border = BorderStroke(1.dp, ColorPalette.signInBlue),
        ) {
            Text(text = "Sign Up", color = ColorPalette.signInBlue, fontSize = 20.sp)
        }
    }
}