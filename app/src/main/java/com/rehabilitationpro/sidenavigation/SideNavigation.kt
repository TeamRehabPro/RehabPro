package com.rehabilitationpro.sidenavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.R
import com.rehabilitationpro.Screen
import com.rehabilitationpro.sidenavigation.component.DrawerMenuItem
import kotlinx.coroutines.launch

@Composable
fun SideNavigation(
    navController: NavHostController,
    drawerState: DrawerState,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.75f) // Adjust the width as needed
                    .wrapContentWidth(align = Alignment.End)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text("Menu", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(8.dp))

                    DrawerMenuItem(
                        label = "메인메뉴",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.MainMenu.route)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerMenuItem(
                        label = "공지사항",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.NoticeScreen.Main.route)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerMenuItem(
                        label = "근태",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.AttendanceScreen.Main.route)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerMenuItem(
                        label = "환자예약",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.ReservationScreen.Main.route)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerMenuItem(
                        label = "병원일정",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.ScheduleScreen.Main.route)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerMenuItem(
                        label = "자재관리",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.DashboardScreen.Main.route)
                            scope.launch { drawerState.close() }
                        }
                    )

                    DrawerMenuItem(
                        label = "사내메신저",
                        icon = painterResource(id = R.drawable.test_icon_0808),
                        onClick = {
                            navController.navigate(Screen.MessengerScreen.Main.route)
                            scope.launch { drawerState.close() }
                        }
                    )
                }
            }
        },
        content = content,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth(align = Alignment.End)
    )
}

