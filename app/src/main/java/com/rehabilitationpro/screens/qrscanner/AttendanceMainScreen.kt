// AttendanceMainScreen.kt
package com.rehabilitationpro.screens.qrscanner

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.screens.qrscanner.component.AttendanceButton

@Composable
fun AttendanceMainScreen(navController: NavHostController) {
    var clockInTime by remember { mutableStateOf<String?>(null) }
    var clockOutTime by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var responseText by remember { mutableStateOf<String?>(null) } // For displaying the response

    // time : QR 스캔 후 설정된 시간
    navController.currentBackStackEntry?.savedStateHandle?.get<String>("scannedTime")?.let { time ->
        if (clockInTime == null) {
            clockInTime = time
            sendAttendanceRequest(clockInTime, null) { response -> responseText = response }
        } else if (clockOutTime == null) {
            clockOutTime = time
            sendAttendanceRequest(clockInTime, clockOutTime) { response -> responseText = response }
        }
    }

    Scaffold{
        innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.padding(8.dp))
            }

            // 출근 시간 및 퇴근 시간 표시
            if (clockInTime != null) {
                Text(text = "출근시간: $clockInTime", modifier = Modifier.padding(top = 16.dp))
            }
            if (clockOutTime != null) {
                Text(text = "퇴근시간: $clockOutTime", modifier = Modifier.padding(top = 16.dp))
            }
            // Display the server response
            if (responseText != null) {
                Text(text = "서버 응답: $responseText", modifier = Modifier.padding(top = 16.dp))
            }

            // 출근/퇴근 QR 코드 스캔 버튼
            AttendanceButton(
                navController = navController,
                clockInTime = clockInTime,
                clockOutTime = clockOutTime,
                setErrorMessage = { errorMessage = it }
            )
        }
    }
}