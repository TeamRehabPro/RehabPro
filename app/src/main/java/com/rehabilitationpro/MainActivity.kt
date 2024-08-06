package com.rehabilitationpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.screen.login.LoginScreen
import com.rehabilitationpro.screen.notice.NoticeScreen
import com.rehabilitationpro.screen.schedule.ScheduleScreen
import com.rehabilitationpro.ui.theme.RehabPROTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RehabPROTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }

        composable("main") { MainMenu(navController) }
        composable("notice") { NoticeScreen(navController) }
        composable("schedule") { ScheduleScreen(navController) }
    }
}

@Composable
fun MainMenu(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("notice") },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Notice")
        }
        Button(
            onClick = { navController.navigate("schedule") },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Go to Schedule")
        }
    }
}