package com.rehabilitationpro.screens.qrscanner.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.screens.qrscanner.getShiftEndTime
import com.rehabilitationpro.screens.qrscanner.getShiftStartTime

@Composable
fun AttendanceButton(
    navController: NavHostController,
    clockInTime: String?,
    clockOutTime: String?,
    setErrorMessage: (String?) -> Unit
) {
    Button(
        onClick = {
            handleAttendanceAction(navController, clockInTime, clockOutTime, setErrorMessage)
        },
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Text(text = if (clockInTime == null) "출근" else "퇴근")
    }
}

fun handleAttendanceAction(
    navController: NavHostController,
    clockInTime: String?,
    clockOutTime: String?,
    setErrorMessage: (String?) -> Unit
) {
    val currentTime = System.currentTimeMillis()
    if (clockInTime == null) { // 출근하지 않은 상태
        val shiftStartTime = getShiftStartTime()

        if (currentTime < shiftStartTime - 3600000) { // 1시간 전
            setErrorMessage("아직 출근시간이 아닙니다")
        } else {
            setErrorMessage(null)
            navController.navigate(Screen.QR.route + "?action=clockin")
        }
    } else if (clockOutTime == null) { // 출근 후 퇴근하지 않은 상태
        val shiftEndTime = getShiftEndTime()

        if (currentTime < shiftEndTime) {
            setErrorMessage("현재는 근무시간입니다")
        } else {
            setErrorMessage(null)
            navController.navigate(Screen.QR.route + "?action=clockout")
        }
    }
}
