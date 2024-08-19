package com.rehabilitationpro.screens.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rehabilitationpro.R
import com.rehabilitationpro.Screen
import com.rehabilitationpro.ui.theme.ColorPalette
import com.rehabilitationpro.widgets.CustomButton

@Composable
fun OnboardingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [1]
        StartScreenImage()
        Spacer(modifier = Modifier.height(24.dp))

        // [2] Move to Login Page
        SignInButton(onClick = { navController.navigate(Screen.SignIn.route) })
        Spacer(modifier = Modifier.height(16.dp))

        // [3] Move to Account Creation Page
        SignUpButton(onClick = { navController.navigate(Screen.SignUp.route) })
    }
}

@Composable
fun StartScreenImage() {
    Box(modifier = Modifier.fillMaxHeight(0.6f)) {
        Image(
            painter = painterResource(id = R.drawable.doctor), // 이미지 경로 : res/drawable 에서 불러올 수 있음.
            modifier = Modifier.fillMaxSize(),  // 이미지 전체 크기에 맞춤
            contentDescription = null,
        )
        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Let's Get Started!", fontSize = 24.sp, fontWeight = FontWeight.Bold
            )
            Text(
                text = "Start using our apps and upgrade your way to manage employees",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = ColorPalette.textGray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}


@Composable
fun SignInButton(
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
fun SignUpButton(
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
