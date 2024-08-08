package com.rehabilitationpro.screens.menus.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
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
import com.rehabilitationpro.Screen
import com.rehabilitationpro.bar.TopBar
import java.util.Calendar

@Composable
fun AttendanceMainScreen(navController: NavHostController, drawerState: DrawerState) {
    val currentRoute = Screen.AttendanceScreen.Main.route
    var clockInTime by remember { mutableStateOf<String?>(null) }
    var clockOutTime by remember { mutableStateOf<String?>(null) }
    var currentAction by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    navController.currentBackStackEntry?.savedStateHandle?.get<String>("scannedTime")?.let { time ->
        if (currentAction == "clockin") {
            clockInTime = time
        } else if (currentAction == "clockout") {
            clockOutTime = time
        }
        currentAction = null
    }

    Scaffold(
        topBar = { TopBar(navController, drawerState, currentRoute) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "출퇴근 등록", modifier = Modifier.align(Alignment.Start))

            if (errorMessage != null) {
                Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.padding(8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color.LightGray),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "출근하기")
                    clockInTime?.let {
                        Text(text = it)
                    }
                }
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "퇴근하기")
                    clockOutTime?.let {
                        Text(text = it)
                    }
                }
            }

            Button(
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    val shiftStartTime = getShiftStartTime()
                    val shiftEndTime = getShiftEndTime()

                    when {
                        clockInTime == null -> {
                            if (currentTime < shiftStartTime - 3600000) { // 1시간 전
                                errorMessage = "아직 출근시간이 아닙니다"
                            } else {
                                errorMessage = null
                                currentAction = "clockin"
                                navController.navigate(Screen.AttendanceScreen.QR.route + "?action=clockin")
                            }
                        }
                        clockOutTime == null -> {
                            if (currentTime < shiftEndTime) {
                                errorMessage = "현재는 근무시간입니다"
                            } else {
                                errorMessage = null
                                currentAction = "clockout"
                                navController.navigate(Screen.AttendanceScreen.QR.route + "?action=clockout")
                            }
                        }
                        else -> {
                            errorMessage = "이미 출퇴근 기록이 있습니다"
                        }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "QR 코드 스캔")
            }
        }
    }
}
fun getShiftStartTime(): Long {
    // 근무시간 09:00을 밀리초로 변환하여 반환
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 9)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar.timeInMillis
}

fun getShiftEndTime(): Long {
    // 근무시간 18:00을 밀리초로 변환하여 반환
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar.timeInMillis
}
