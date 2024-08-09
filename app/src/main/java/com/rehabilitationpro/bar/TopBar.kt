package com.rehabilitationpro.bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.rehabilitationpro.R
import com.rehabilitationpro.Screen
import com.rehabilitationpro.ui.theme.ColorPalette
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController, drawerState: DrawerState, currentRoute: String?) {
    val colors = ColorPalette()
    val scope = rememberCoroutineScope()

    // Define the title based on the current route
    val title = when (currentRoute) {
        Screen.MainMenu.route -> "홈 화면"
        Screen.NoticeScreen.Main.route -> "공지사항"
        Screen.AttendanceScreen.Main.route -> "근태"
        Screen.ReservationScreen.Main.route -> "환자예약"
        Screen.ScheduleScreen.Main.route -> "병원일정"
        Screen.DashboardScreen.Main.route -> "자재관리"
        Screen.MessengerScreen.Main.route -> "사내메신저"
        else -> "TopBar.kt 에서 이름 설정 필요"
    }

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colors.mySkyBlue
        ),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.MainMenu.route) }) {
                Icon(painter = painterResource(id = R.drawable.test_icon_0808), contentDescription = "Logo", tint = colors.myWhite)
            }
        },
        title = { Text(text = title, color = colors.myWhite) },
        actions = {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer", tint = colors.myWhite)
            }
        },
    )
}
