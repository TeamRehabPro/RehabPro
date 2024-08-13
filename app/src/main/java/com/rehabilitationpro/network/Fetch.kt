package com.rehabilitationpro.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.rehabilitationpro.screens.home.component.DayInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun fetchReservationData(userId: String, selectedDate: DayInfo, onResult: (Result<String>) -> Unit) {
    val today = LocalDate.now()

    // Reconstruct the selected LocalDate based on the selectedDay's dayOfMonth
    val selectedLocalDate = today.withDayOfMonth(selectedDate.dayOfMonth)

    val formattedDate = selectedLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val url = "http://192.168.45.240:8080/reservations/id/$userId/date/$formattedDate"

    HttpClient.sendGetRequest(url, onResult)
}