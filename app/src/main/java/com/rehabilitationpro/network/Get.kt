package com.rehabilitationpro.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.rehabilitationpro.network.HttpClient.sendGetRequest
import com.rehabilitationpro.screens.home.component.DayInfo
import com.rehabilitationpro.screens.notice.Notice
import org.json.JSONArray
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun getReservationData(userId: String, selectedDate: DayInfo, onResult: (Result<String>) -> Unit) {
    val today = LocalDate.now()

    // Reconstruct the selected LocalDate based on the selectedDay's dayOfMonth
    val selectedLocalDate = today.withDayOfMonth(selectedDate.dayOfMonth)

    val formattedDate = selectedLocalDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val url = "http://192.168.45.240:8080/reservations/id/$userId/date/$formattedDate"

    sendGetRequest(url, onResult)
}

fun getNoticeData(onResult: (Result<List<Notice>>) -> Unit) {
    val url = "http://192.168.45.240:8080/notice"
    sendGetRequest(url) { result ->
        result.fold(
            onSuccess = { jsonString ->
                try {
                    val jsonArray = JSONArray(jsonString)
                    val notices = mutableListOf<Notice>()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        notices.add(
                            Notice(
                                id = jsonObject.getString("employeeId"),
                                title = jsonObject.getString("title"),
                                description = jsonObject.getString("content"),
                                timestamp = jsonObject.getString("creationDate")
                            )
                        )
                    }
                    onResult(Result.success(notices))
                } catch (e: Exception) {
                    onResult(Result.failure(e))
                }
            },
            onFailure = { e ->
                onResult(Result.failure(e))
            }
        )
    }
}