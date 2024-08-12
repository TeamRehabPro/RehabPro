// HomeScreen.kt
package com.rehabilitationpro.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.screens.home.component.AttendanceSection
import com.rehabilitationpro.screens.home.component.DayInfo
import com.rehabilitationpro.screens.home.component.MenuNavigation
import com.rehabilitationpro.screens.home.component.MyProfile
import com.rehabilitationpro.screens.home.component.SmallCalendarWidgetSection
import com.rehabilitationpro.ui.theme.RehabPROTheme

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
        MyProfile()
        Spacer(modifier = Modifier.height(32.dp))

        // [2] 출/퇴근 QR 위젯
        AttendanceSection(navController = navController)

        // [3] Menu
        MenuNavigation(navController = navController)

        // [4] 이번주 일정 (5일 단위)
        var selectedDay by remember { mutableStateOf<DayInfo?>(null) }
        SmallCalendarWidgetSection(
            selectedDay = selectedDay,
            onDaySelected = { selectedDay = it }
        )
        // [5] Details 위젯

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    RehabPROTheme { HomeScreen(navController = navController) }
}
