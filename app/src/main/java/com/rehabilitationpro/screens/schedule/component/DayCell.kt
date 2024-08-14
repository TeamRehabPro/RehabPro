package com.rehabilitationpro.screens.schedule.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.ui.theme.ColorPalette
import java.time.LocalDate

@Composable
fun DayCell(
    day: String,
    isSelected: Boolean,
    isCurrentDay: Boolean,
    isCurrentMonth: Boolean,
    events: List<EventType>,
    onDateSelected: () -> Unit,
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clickable(onClick = onDateSelected)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            // 날짜 숫자를 감싸는 둥근 사각형 배경
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .offset(y = (-4).dp)
                    .background(
                        color = if (isSelected) ColorPalette.primaryBlue else Color.Transparent,
                        shape = RoundedCornerShape(4.dp)  // 모서리가 둥근 사각형으로 변경
                    )
                    .then(
                        if (isCurrentDay && !isSelected)
                            Modifier.border(1.dp, ColorPalette.primaryBlue, RoundedCornerShape(4.dp))
                        else
                            Modifier
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = day,
                    color = when {
                        isSelected -> Color.White
                        !isCurrentMonth -> ColorPalette.borderGray
                        else -> Color.Black
                    },
                    textAlign = TextAlign.Center,
                )
            }
        }

        // 여러 일정 표시 아이콘을 현재 달의 날짜에만 표시
        if (events.isNotEmpty() && isCurrentMonth) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                events.forEach { eventType ->
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .clip(CircleShape)
                            .background(
                                color = when (eventType) {
                                    EventType.VACATION -> Color.Red
                                    EventType.SEMINAR -> Color.Blue
                                    EventType.BUSINESS_TRIP -> Color.Green
                                }
                            )
                    )
                }
            }
        }
    }
}

enum class EventType { VACATION, SEMINAR, BUSINESS_TRIP }

@RequiresApi(Build.VERSION_CODES.O)
fun getEventsForDate(date: LocalDate): List<EventType> {
    val events = mutableListOf<EventType>()
    if (date.dayOfMonth % 3 == 0) events.add(EventType.VACATION)
    if (date.dayOfMonth % 5 == 0) events.add(EventType.SEMINAR)
    if (date.dayOfMonth % 2 == 0) events.add(EventType.BUSINESS_TRIP)
    return events
}