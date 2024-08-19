package com.rehabilitationpro.screens.home.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.ui.theme.ColorPalette
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//@Composable
//fun AttendanceSection(navController: NavController) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        shape = RoundedCornerShape(8.dp),
//        colors = CardDefaults.cardColors(containerColor = ColorPalette.inputBoxGray),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = "Attendance", style = MaterialTheme.typography.headlineMedium)
//            Text(text = "Work Shift : 09:00 - 18:00", style = MaterialTheme.typography.bodyMedium)
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceEvenly
//            ) {
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(text = "--:--")
//                    Text(text = "Clock in", style = MaterialTheme.typography.bodyMedium)
//                }
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(text = "--:--")
//                    Text(text = "Clock out", style = MaterialTheme.typography.bodyMedium)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = { navController.navigate(Screen.QR.route) },
//                modifier = Modifier.fillMaxWidth(),
//                colors = ButtonDefaults.buttonColors(containerColor = ColorPalette.myGray400)
//            ) {
//                // Text(text = "Touch for Clock in 🌤", color = Color.White)
//                // Text(text = "Touch for Clock out 😎", color = Color.White)
//                Text(text = "See you Tomorrow 🌙", color = Color.White)
//            }
//        }
//    }
//}


class AttendanceViewModel : ViewModel() {
    private val _clockInTime = MutableStateFlow("--:--")
    val clockInTime = _clockInTime.asStateFlow()

    private val _clockOutTime = MutableStateFlow("--:--")
    val clockOutTime = _clockOutTime.asStateFlow()

    private val _buttonText = MutableStateFlow("Touch for Clock in 🌤")
    val buttonText = _buttonText.asStateFlow()

    private val _isButtonEnabled = MutableStateFlow(true)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    fun clockIn() {
        viewModelScope.launch {
            delay(1000) // 1초 지연
            _clockInTime.value = "08:50"
            _buttonText.value = "Touch for Clock out 😎"
        }
    }

    fun clockOut() {
        viewModelScope.launch {
            delay(1000) // 1초 지연
            _clockOutTime.value = "18:03"
            _buttonText.value = "See you Tomorrow 🌙"
            _isButtonEnabled.value = false
        }
    }
}

@Composable
fun AttendanceSection(navController: NavController, viewModel: AttendanceViewModel) {
    val clockInTime by viewModel.clockInTime.collectAsState()
    val clockOutTime by viewModel.clockOutTime.collectAsState()
    val buttonText by viewModel.buttonText.collectAsState()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = ColorPalette.inputBoxGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Attendance", style = MaterialTheme.typography.headlineMedium)
            Text(text = "Work Shift : 09:00 - 18:00", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = clockInTime)
                    Text(text = "Clock in", style = MaterialTheme.typography.bodyMedium)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = clockOutTime)
                    Text(text = "Clock out", style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.QR.route)
                    when {
                        clockInTime == "--:--" -> viewModel.clockIn()
                        clockOutTime == "--:--" -> viewModel.clockOut()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = ColorPalette.primaryBlue),
                enabled = isButtonEnabled
            ) {
                Text(text = buttonText, color = Color.White)
            }
        }
    }
}