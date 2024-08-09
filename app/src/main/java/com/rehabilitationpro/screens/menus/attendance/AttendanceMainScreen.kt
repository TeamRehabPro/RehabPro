// AttendanceMainScreen.kt

package com.rehabilitationpro.screens.menus.attendance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttendanceMainScreen(navController: NavHostController) {
    var clockInTime by remember { mutableStateOf<String?>(null) }
    var clockOutTime by remember { mutableStateOf<String?>(null) }
    var currentAction by remember { mutableStateOf<String?>(null) }

    navController.currentBackStackEntry?.savedStateHandle?.get<String>("scannedTime")?.let { time ->
        if (currentAction == "clockin") {
            clockInTime = time
        } else if (currentAction == "clockout") {
            clockOutTime = time
        }
        currentAction = null
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Attendance Management") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.MainMenu.route)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "출퇴근 등록", modifier = Modifier.align(Alignment.Start))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(Color.LightGray),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "출근하기")
                    clockInTime?.let {
                        Text(text = it)
                    }
                    Button(
                        onClick = {
                            currentAction = "clockin"
                            navController.navigate(Screen.AttendanceScreen.QR.route + "?action=clockin")
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "출근")
                    }
                }
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "퇴근하기")
                    clockOutTime?.let {
                        Text(text = it)
                    }
                    Button(
                        onClick = {
                            currentAction = "clockout"
                            navController.navigate(Screen.AttendanceScreen.QR.route + "?action=clockout")
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(text = "퇴근")
                    }
                }
            }
        }
    }
}
