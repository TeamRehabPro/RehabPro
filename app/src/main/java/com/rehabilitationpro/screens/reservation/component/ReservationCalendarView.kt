// ReservationCalendarView.kt
package com.rehabilitationpro.screens.reservation.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarView(
    currentMonth: YearMonth,
    currentDate: LocalDate,
    onMonthChange: (YearMonth) -> Unit,
    onTodayClick: () -> Unit,
    isYearView: MutableState<Boolean>,
    onYearChange: (Int) -> Unit
) {
    if (isYearView.value) {
        YearView(currentYear = currentMonth.year, currentDate = currentDate, onMonthSelect = {
            isYearView.value = false
            onMonthChange(YearMonth.of(currentMonth.year, it))
        }, onYearChange = onYearChange)
    } else {
        MonthView(
            currentMonth = currentMonth,
            currentDate = currentDate,
            onMonthChange = onMonthChange,
            onTodayClick = onTodayClick,
            onExpandClick = { isYearView.value = true }
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthView(
    currentMonth: YearMonth,
    currentDate: LocalDate,
    onMonthChange: (YearMonth) -> Unit,
    onTodayClick: () -> Unit,
    onExpandClick: () -> Unit
) {
    val daysInMonth = currentMonth.lengthOfMonth()
    val firstDayOfMonth = currentMonth.atDay(1).dayOfWeek.value % 7

    Column(
        modifier = Modifier
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount > 0) {
                        onMonthChange(currentMonth.minusMonths(1)) // 왼쪽으로 드래그 시 이전 달로 이동
                    } else {
                        onMonthChange(currentMonth.plusMonths(1)) // 오른쪽으로 드래그 시 다음 달로 이동
                    }
                }
            }
    ) {
        // Month navigation row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onMonthChange(currentMonth.minusMonths(1)) }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Month")
            }
            Text(
                text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            IconButton(onClick = { onMonthChange(currentMonth.plusMonths(1)) }) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Next Month")
            }
        }

        // Today and Expand buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = onTodayClick) {
                Text(text = "Today")
            }
            Button(onClick = onExpandClick) {
                Text(text = "Expand")
            }
        }

        // Days of the week header
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false,
            content = {
                items(listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")) { day ->
                    Text(
                        text = day,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    )
                }
            }
        )

        // Calendar days
        val dayItems = (1..daysInMonth).map { it.toString() }
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            content = {
                items(firstDayOfMonth) {
                    Box(modifier = Modifier.size(40.dp))
                }
                items(dayItems) { day ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(if (day.toInt() == currentDate.dayOfMonth && currentMonth == YearMonth.from(currentDate)) Color.LightGray else Color.Transparent)
                            .clickable { /* Handle day click */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = day)
                    }
                }
            }
        )
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearView(
    currentYear: Int,
    currentDate: LocalDate,
    onMonthSelect: (Int) -> Unit,
    onYearChange: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Year navigation row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onYearChange(currentYear - 1) }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Previous Year")
            }
            Text(
                text = "$currentYear",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )
            IconButton(onClick = { onYearChange(currentYear + 1) }) {
                Icon(Icons.Filled.ArrowForward, contentDescription = "Next Year")
            }
        }

        val months = listOf(
            "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December"
        )

        for (row in 0 until 4) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                for (col in 0 until 3) {
                    val monthIndex = row * 3 + col + 1
                    val yearMonth = YearMonth.of(currentYear, monthIndex)
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(120.dp)
                            .background(if (yearMonth == YearMonth.from(currentDate)) Color.LightGray else Color.Transparent)
                            .clickable { onMonthSelect(monthIndex) },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = months[monthIndex - 1], fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            MiniCalendarView(yearMonth = yearMonth, currentDate = currentDate)
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MiniCalendarView(yearMonth: YearMonth, currentDate: LocalDate) {
    val daysInMonth = yearMonth.lengthOfMonth()
    val firstDayOfMonth = yearMonth.atDay(1).dayOfWeek.value % 7

    Column(modifier = Modifier.padding(4.dp)) {
        // Calendar days
        var dayOfMonth = 1 - firstDayOfMonth
        for (week in 0 until 6) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly // 변경된 부분
            ) {
                for (day in 0..6) {
                    if (dayOfMonth in 1..daysInMonth) {
                        Box(
                            modifier = Modifier
                                .size(15.dp)
                                .background(if (dayOfMonth == currentDate.dayOfMonth && yearMonth == YearMonth.from(currentDate)) Color.LightGray else Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = dayOfMonth.toString(), fontSize = 8.sp)
                        }
                    } else {
                        Box(modifier = Modifier.size(15.dp))
                    }
                    dayOfMonth++
                }
            }
        }
    }
}
