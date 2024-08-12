package com.rehabilitationpro.screens.home.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.ui.theme.ColorPalette
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

data class DayInfo(
    val dayOfWeek: String,
    val dayOfMonth: Int,
    val isToday: Boolean
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SmallCalendarWidgetSection(
    selectedDay: DayInfo? = null, // selectedDay의 초기값을 null로 설정
    onDaySelected: (DayInfo) -> Unit
) {
    val today = LocalDate.now()
    val days = (0..4).map { dayOffset ->
        today.plusDays(dayOffset.toLong()).let { date ->
            DayInfo(
                dayOfWeek = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                dayOfMonth = date.dayOfMonth,
                isToday = dayOffset == 0
            )
        }
    }

    // 만약 selectedDay가 null이라면 오늘 날짜를 기본값으로 사용
    val defaultSelectedDay = selectedDay ?: days.first { it.isToday }

    SmallCalendarWidget(
        days = days,
        selectedDay = defaultSelectedDay,
        onDayClick = onDaySelected
    )
}

@Composable
fun SmallCalendarWidget(
    days: List<DayInfo>,
    selectedDay: DayInfo?,
    onDayClick: (DayInfo) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        days.forEach { day ->
            val isSelected = day == selectedDay
            DayItem(
                day = day,
                isSelected = isSelected,
                onClick = { onDayClick(day) }
            )
        }
    }
}

@Composable
fun DayItem(day: DayInfo, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) ColorPalette.primaryBlue else ColorPalette.inputBoxGray
    val textColor = if (isSelected) Color.White else Color.Black
    val size = if (isSelected) 64.dp else 56.dp // 선택된 항목의 크기를 키우고 선택되지 않은 항목의 크기를 줄임

    // 모든 DayItem이 동일한 크기의 박스 안에 배치되도록 함
    val modifier = Modifier
        .size(64.dp) // 고정된 크기의 박스를 유지
        .clip(RoundedCornerShape(8.dp))
        .background(Color.Transparent) // 기본 배경을 투명하게 설정
        .clickable(onClick = onClick)
        .padding((64.dp - size) / 2) // 선택되지 않은 항목에 여유 공간을 추가하여 크기를 줄임

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.background(backgroundColor)
    ) {
        Text(text = day.dayOfWeek, color = textColor, style = MaterialTheme.typography.bodyMedium)
        Text(text = day.dayOfMonth.toString(), color = textColor, style = MaterialTheme.typography.bodyLarge)
    }
}
