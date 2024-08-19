package com.rehabilitationpro.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.network.getReservationData
import com.rehabilitationpro.screens.home.component.AttendanceSection
import com.rehabilitationpro.screens.home.component.DayInfo
import com.rehabilitationpro.screens.home.component.HomeScreenHeader
import com.rehabilitationpro.screens.home.component.MenuNavigation
import com.rehabilitationpro.screens.home.component.SmallCalendarWidgetSection
import com.rehabilitationpro.ui.theme.ColorPalette
import com.rehabilitationpro.ui.theme.RehabPROTheme
import kotlinx.coroutines.launch
import org.json.JSONArray

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [1] 프로필 + 상단 제목
        HomeScreenHeader()
        Spacer(modifier = Modifier.height(32.dp))

        // [2] 출/퇴근 QR 위젯
        AttendanceSection(navController = navController)

        // [3] Menu
        MenuNavigation(navController = navController)

        // [4] 이번주 일정 (5일 단위)
        var selectedDay by remember { mutableStateOf<DayInfo?>(null) }
        var reservationData by remember { mutableStateOf<List<Reservation>?>(null) }
        var isLoading by remember { mutableStateOf(false) }
        var error by remember { mutableStateOf<String?>(null) }

        val userId = "003"
        val coroutineScope = rememberCoroutineScope()

        Column {
            SmallCalendarWidgetSection(
                selectedDay = selectedDay,
                onDaySelected = { newSelectedDay ->
                    selectedDay = newSelectedDay
                    selectedDay?.let { date ->
                        coroutineScope.launch {
                            isLoading = true
                            getReservationData(userId, date) { result ->
                                isLoading = false
                                result.fold(
                                    onSuccess = { data ->
                                        reservationData = parseReservationData(data)
                                        error = null
                                    },
                                    onFailure = { exception ->
                                        error = exception.message ?: "알 수 없는 오류가 발생했습니다."
                                        reservationData = null
                                    }
                                )
                            }
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                isLoading -> { CircularProgressIndicator() }

                error != null -> {
                    //Text("오류: $error")
                    Text("오늘의 예약은 없습니다.")
                }

                reservationData != null -> {
                    LazyColumn {
                        items(reservationData!!) { reservation ->
                            PatientReservationCard(reservation)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PatientReservationCard(reservation: Reservation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorPalette.inputBoxGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("고객 이름: ${reservation.customerName}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("예약 시간: ${reservation.reservationTime}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("상세 내용: ${reservation.detail}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Assuming that the fetchReservationData function returns a JSON string, we need a data class and a parser function
data class Reservation(
    val customerName: String,
    val reservationTime: String,
    val detail: String
)

fun parseReservationData(jsonData: String): List<Reservation> {
    // You would use a JSON parsing library like kotlinx.serialization or Moshi to parse the JSON string.
    // Here’s a simple manual parser as an example:
    val reservations = mutableListOf<Reservation>()
    // Parse the jsonData assuming it is in a known format
    val jsonArray = JSONArray(jsonData)
    for (i in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(i)
        val customerName = jsonObject.getString("customerName")
        val reservationTime = jsonObject.getString("reservationTime")
        val detail = jsonObject.getString("detail")
        reservations.add(Reservation(customerName, reservationTime, detail))
    }
    return reservations
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    RehabPROTheme { HomeScreen(navController = navController) }
}