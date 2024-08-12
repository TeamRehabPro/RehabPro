package com.rehabilitationpro.screens.splash.component

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
import com.rehabilitationpro.R
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun StartScreenImage(){
    Box(modifier = Modifier.fillMaxHeight(0.6f)) {
        Image(
            painter = painterResource(id = R.drawable.doctor), // 이미지 경로 : res/drawable 에서 불러올 수 있음.
            modifier = Modifier.fillMaxSize(),  // 이미지 전체 크기에 맞춤
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Let's Get Started!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
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
