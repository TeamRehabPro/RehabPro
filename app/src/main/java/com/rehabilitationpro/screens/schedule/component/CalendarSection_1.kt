package com.rehabilitationpro.screens.schedule.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarTitle_1(
    currentYearMonth: YearMonth,
    onYearMonthChanged: (YearMonth) -> Unit
) {
    var showYearDropdown by remember { mutableStateOf(false) }
    var showMonthDropdown by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Year selector
        Box {
            Row(
                modifier = Modifier.clickable { showYearDropdown = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${currentYearMonth.year}",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Year")
            }
            DropdownMenu(
                expanded = showYearDropdown,
                onDismissRequest = { showYearDropdown = false }
            ) {
                (currentYearMonth.year - 5..currentYearMonth.year + 5).forEach { year ->
                    DropdownMenuItem(
                        text = { Text(year.toString()) },
                        onClick = {
                            onYearMonthChanged(YearMonth.of(year, currentYearMonth.month))
                            showYearDropdown = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Month selector
        Box {
            Row(
                modifier = Modifier.clickable { showMonthDropdown = true },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = currentYearMonth.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Select Month")
            }
            DropdownMenu(
                expanded = showMonthDropdown,
                onDismissRequest = { showMonthDropdown = false }
            ) {
                Month.entries.forEach { month ->
                    DropdownMenuItem(
                        text = { Text(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)) },
                        onClick = {
                            onYearMonthChanged(YearMonth.of(currentYearMonth.year, month))
                            showMonthDropdown = false
                        }
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarComponent_1() {
    var currentYearMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        CalendarTitle_1(
            currentYearMonth = currentYearMonth,
            onYearMonthChanged = { newYearMonth ->
                currentYearMonth = newYearMonth
                // Adjust selectedDate if it's not in the new month
                if (selectedDate.year != newYearMonth.year || selectedDate.month != newYearMonth.month) {
                    selectedDate = newYearMonth.atDay(1)
                }
            }
        )

        // 요일 헤더
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 달력 그리드
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth()
        ) {
            val firstDayOfWeek = currentYearMonth.atDay(1).dayOfWeek.value % 7
            val daysInMonth = currentYearMonth.lengthOfMonth()

            // 이전 달의 날짜들
            val previousMonth = currentYearMonth.minusMonths(1)
            val daysInPreviousMonth = previousMonth.lengthOfMonth()
            repeat(firstDayOfWeek) { day ->
                val date = previousMonth.atDay(daysInPreviousMonth - firstDayOfWeek + day + 1)
                item {
                    DayCell(
                        day = date.dayOfMonth.toString(),
                        isSelected = false,
                        isCurrentDay = false,
                        isCurrentMonth = false,
                        events = getEventsForDate(date),
                        onDateSelected = {}
                    )
                }
            }

            // 현재 달의 날짜들
            repeat(daysInMonth) { day ->
                val date = currentYearMonth.atDay(day + 1)
                item {
                    DayCell(
                        day = (day + 1).toString(),
                        isSelected = date == selectedDate,
                        isCurrentDay = date == LocalDate.now(),
                        isCurrentMonth = true,
                        events = getEventsForDate(date),
                        onDateSelected = { selectedDate = date }
                    )
                }
            }

            // 다음 달의 날짜들
            val remainingCells = 42 - (firstDayOfWeek + daysInMonth)
            repeat(remainingCells) { day ->
                val date = currentYearMonth.plusMonths(1).atDay(day + 1)
                item {
                    DayCell(
                        day = (day + 1).toString(),
                        isSelected = false,
                        isCurrentDay = false,
                        isCurrentMonth = false,
                        events = getEventsForDate(date),
                        onDateSelected = {}
                    )
                }
            }
        }
    }
}