package com.rehabilitationpro.screens.menus.reservation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.bar.TopBar
import com.rehabilitationpro.screens.menus.reservation.component.CalendarView
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReservationMainScreen(navController: NavHostController, drawerState: DrawerState) {
    val currentDate = LocalDate.now()
    val currentMonth = remember { mutableStateOf(YearMonth.now()) }
    val isYearView = remember { mutableStateOf(false) }

    val currentRoute = Screen.ReservationScreen.Main.route

    Scaffold(
        topBar = { TopBar(navController, drawerState, currentRoute) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Display the current month's calendar
            CalendarView(
                currentMonth = currentMonth.value,
                currentDate = currentDate,
                onMonthChange = { newMonth ->
                    currentMonth.value = newMonth
                },
                onTodayClick = {
                    currentMonth.value = YearMonth.now()
                },
                isYearView = isYearView,
                onYearChange = { newYear ->
                    currentMonth.value = YearMonth.of(newYear, currentMonth.value.month)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Display today's reservations
            Text(
                text = "Today's Reservations",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Sample reservations for today
            val todayReservations =
                listOf("Patient A - 10:00 AM", "Patient B - 11:30 AM", "Patient C - 1:00 PM")
            LazyColumn {
                items(todayReservations) { reservation ->
                    Text(
                        text = reservation,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
