// MenuScreen.kt
package com.rehabilitationpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1: 공지사항 페이지
        Button(
            onClick = { navController.navigate(Screen.NoticeScreen.Main.route) }, // Updated to use Notice.MainScreen
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Notice")
        }
        // 2: 근태 확인 페이지
        Button(
            onClick = { navController.navigate(Screen.AttendanceScreen.Main.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Attendance Management")
        }
        // 3: 환자 예약 관리
        Button(
            onClick = { navController.navigate(Screen.ReservationScreen.Main.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Patient Reservation")
        }
        // 4: 병원 일정 페이지
        Button(
            onClick = { navController.navigate(Screen.ScheduleScreen.Main.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Schedule")
        }
        // 5: 병상, 장비 현황
        Button(
            onClick = { navController.navigate(Screen.DashboardScreen.Main.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Equipment Dashboard")
        }
        // 6: 원내 메신저
        Button(
            onClick = { navController.navigate(Screen.MessengerScreen.Main.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Messenger")
        }
    }
}