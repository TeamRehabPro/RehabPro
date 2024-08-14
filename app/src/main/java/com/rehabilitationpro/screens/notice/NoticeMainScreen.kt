package com.rehabilitationpro.screens.notice

import NoticeCard
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.ui.theme.RehabPROTheme
import com.rehabilitationpro.widgets.NoticeScreenHeader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoticeMainScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoticeScreenHeader(onBackClick = { navController.navigate(Screen.Home.route) })

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notices) { notice ->
                NoticeCard(notice = notice)
            }
        }
    }
}

data class Notice(val title: String, val description: String, val timestamp: String) {
    val id: String get() = timestamp.replace("[-: ]".toRegex(), "")
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoticeMainScreenPreview() {
    val navController = rememberNavController()
    RehabPROTheme { NoticeMainScreen(navController = navController) }
}