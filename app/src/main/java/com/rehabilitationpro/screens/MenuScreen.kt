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
import com.rehabilitationpro.navigation.Screen

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1: 공지사항 페이지
        Button(
            onClick = { navController.navigate(Screen.Notice.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Notice")
        }
        // 2: 근태 확인 페이지
        Button(
            onClick = { navController.navigate(Screen.Attendance.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Attendance Management")
        }
        // 3: 환자 예약 관리
        Button(
            onClick = { navController.navigate(Screen.Reservation.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Patient Reservation")
        }
        // 4: 병원 일정 페이지
        Button(
            onClick = { navController.navigate(Screen.Schedule.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Schedule")
        }
        // 5: 병상, 장비 현황
        Button(
            onClick = { navController.navigate(Screen.Dashboard.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Equipment Dashboard")
        }
        // 6: 원내 메신저
        Button(
            onClick = { navController.navigate(Screen.Messenger.route) },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Messenger")
        }
    }
}
