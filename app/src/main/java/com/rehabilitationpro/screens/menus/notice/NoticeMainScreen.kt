// NoticeMainScreen.kt
package com.rehabilitationpro.screens.menus.notice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.bar.TopBar
import com.rehabilitationpro.screens.menus.notice.component.NoticeCard

// Generate the ID based on the timestamp
data class Notice(val title: String, val description: String, val timestamp: String) {
    val id: String get() = timestamp.replace("[-: ]".toRegex(), "")
}

@Composable
fun NoticeMainScreen(navController: NavHostController, drawerState: DrawerState) {
    val currentRoute = Screen.NoticeScreen.Main.route

    Scaffold(
        topBar = { TopBar(navController, drawerState, currentRoute) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notice Screen",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(notices) { notice ->
                    NoticeCard(notice) {
                        navController.navigate(Screen.NoticeScreen.Detail.createRoute(notice.id))
                    }
                }
            }
        }
    }
}

val notices = listOf(
    Notice("Notice 1", "Description for Notice 1", "2024-08-06 14:30"),
    Notice("Notice 2", "Description for Notice 2", "2024-08-05 11:15"),
    Notice("Notice 3", "Description for Notice 3", "2024-08-04 09:45"),
    Notice("Notice 4", "Description for Notice 4", "2024-08-03 17:00"),
    Notice("Notice 5", "Description for Notice 5", "2024-08-02 12:30"),
    Notice("Notice 6", "Description for Notice 6", "2024-08-01 16:20"),
    Notice("Notice 7", "Description for Notice 7", "2024-07-31 10:10"),
    Notice("Notice 8", "Description for Notice 8", "2024-07-30 15:55"),
    Notice("Notice 9", "Description for Notice 9", "2024-07-29 13:35"),
    Notice("Notice 10", "Description for Notice 10", "2024-07-28 08:50"),
)