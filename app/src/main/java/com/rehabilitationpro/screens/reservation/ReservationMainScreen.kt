package com.rehabilitationpro.screens.reservation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.screens.reservation.component.CalendarView
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservationMainScreen(navController: NavHostController) {
    val currentDate = LocalDate.now()
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf(currentDate) }
    val isYearView = remember { mutableStateOf(false) }

    Scaffold{
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CalendarView(
                currentMonth = currentMonth.value,
                currentDate = currentDate,
                onMonthChange = { newMonth ->
                    currentMonth.value = newMonth
                },
                onTodayClick = {
                    currentMonth.value = YearMonth.now()
                    selectedDate.value = LocalDate.now()
                },
                isYearView = isYearView,
                onYearChange = { newYear ->
                    currentMonth.value = YearMonth.of(newYear, currentMonth.value.month)
                }
            )

            val reservations = listOf(
                Reservation("John's Birthday", "All day", Color(0xFFB3C7F9)),
                Reservation("Meeting", "15h30 - 17h00", Color(0xFFFFD6C4)),
                Reservation("Romeo and Juliet play", "19h", Color(0xFFFFD6DA))
            )
            ReservationList(selectedDate.value, reservations)
            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservationList(
    selectedDate: LocalDate, // 날짜 정보를 매개변수로 전달
    reservations: List<Reservation> // 예약 목록을 매개변수로 전달
) {
    Text(
        text = "${selectedDate.dayOfMonth} ${selectedDate.month} ${selectedDate.year}",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        reservations.forEach { reservation ->
            ReservationItem(reservation)
        }
    }
}

@Composable
fun ReservationItem(reservation: Reservation) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(reservation.color, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = reservation.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = reservation.time,
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }
}

data class Reservation(
    val title: String,
    val time: String,
    val color: Color
)
