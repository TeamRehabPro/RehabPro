package com.rehabilitationpro.screens.notice

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.rehabilitationpro.Screen
import com.rehabilitationpro.network.getNoticeData
import com.rehabilitationpro.ui.theme.RehabPROTheme
import com.rehabilitationpro.widgets.NoticeCard
import com.rehabilitationpro.widgets.NoticeScreenHeader

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoticeMainScreen(navController: NavHostController) {
    var notices by remember { mutableStateOf<List<Notice>?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(key1 = Unit) {
        getNoticeData { result ->
            result.fold(
                onSuccess = { noticeList ->
                    notices = noticeList
                    isLoading = false
                },
                onFailure = { e ->
                    error = e.message
                    isLoading = false
                }
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoticeScreenHeader(onBackClick = { navController.navigate(Screen.Home.route) })

        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            error != null -> {
                Text("Error: $error", color = Color.Red, modifier = Modifier.padding(16.dp))
            }
            notices.isNullOrEmpty() -> {
                Text("No notices available", modifier = Modifier.padding(16.dp))
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(notices!!) { notice ->
                        NoticeCard(notice = notice)
                    }
                }
            }
        }
    }
}

data class Notice(
    val id: String,
    val title: String,
    val description: String,
    val timestamp: String
)

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NoticeMainScreenPreview() {
    val navController = rememberNavController()
    RehabPROTheme { NoticeMainScreen(navController = navController) }
}