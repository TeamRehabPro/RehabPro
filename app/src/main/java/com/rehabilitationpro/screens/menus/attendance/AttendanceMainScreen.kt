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
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.util.Calendar

@Composable
fun AttendanceMainScreen(navController: NavHostController, drawerState: DrawerState) {
    val currentRoute = Screen.AttendanceScreen.Main.route
    var clockInTime by remember { mutableStateOf<String?>(null) }
    var clockOutTime by remember { mutableStateOf<String?>(null) }
    var currentAction by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val employeeId = "temp_001"

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
            MyApp()
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

            // 출근 QR 코드 스캔 버튼
            Button(
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    val shiftStartTime = getShiftStartTime()

                    if (clockInTime == null) {
                        if (currentTime < shiftStartTime - 3600000) { // 1시간 전
                            errorMessage = "아직 출근시간이 아닙니다"
                        } else {
                            errorMessage = null
                            currentAction = "clockin"
                            navController.navigate(Screen.AttendanceScreen.QR.route + "?action=clockin")
                        }
                    } else {
                        errorMessage = "이미 출근 기록이 있습니다"
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "출근 QR 코드 스캔")
            }

            // 퇴근 QR 코드 스캔 버튼
            Button(
                onClick = {
                    val currentTime = System.currentTimeMillis()
                    val shiftEndTime = getShiftEndTime()

                    if (clockOutTime == null) {
                        if (currentTime < shiftEndTime) {
                            errorMessage = "현재는 근무시간입니다"
                        } else {
                            errorMessage = null
                            currentAction = "clockout"
                            navController.navigate(Screen.AttendanceScreen.QR.route + "?action=clockout")
                        }
                    } else {
                        errorMessage = "이미 퇴근 기록이 있습니다"
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "퇴근 QR 코드 스캔")
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

private val client = OkHttpClient()
private val url = "http://192.168.45.240:8080/attendance"

@Composable
fun MyApp() {
    var responseText by remember { mutableStateOf("Response will be shown here") }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { sendPostRequest { response -> responseText = response } }) {
            Text(text = "Send POST Request")
        }
        Text(text = responseText, modifier = Modifier.padding(top = 16.dp))
    }
}

private fun sendPostRequest(onResult: (String) -> Unit) {
    val json = """
        {
          "employeeId": "sdh queen",
          "date": "2024-08-09",
          "checkInTime": "09:00:00",
          "checkOutTime": "17:00:00",
          "status": "Present"
        }
    """.trimIndent()

    val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
            onResult("Failed to send request: ${e.message}")
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            response.use {
                if (it.isSuccessful) {
                    val responseData = it.body?.string()
                    onResult(responseData ?: "No response from server")
                } else {
                    onResult("Request failed with code: ${it.code}")
                }
            }
        }
    })
}
