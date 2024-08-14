package com.rehabilitationpro.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.rehabilitationpro.network.HttpClient.sendGetRequest
import com.rehabilitationpro.screens.home.component.DayInfo
import com.rehabilitationpro.screens.notice.Notice
import com.rehabilitationpro.screens.schedule.component.Event
import com.rehabilitationpro.screens.schedule.component.EventType
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

@RequiresApi(Build.VERSION_CODES.O)
fun getEventData(date: LocalDate, onResult: (Result<List<Event>>) -> Unit) {
    val employeeId = "003" // Replace with actual employee ID if needed
    val formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    val url = "http://192.168.45.240:8080/leave/id/$employeeId/date/$formattedDate"

    sendGetRequest(url) { result ->
        result.fold(
            onSuccess = { jsonString ->
                try {
                    val jsonArray = JSONArray(jsonString)
                    val events = mutableListOf<Event>()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val eventType = when (jsonObject.getString("leaveType")) {
                            "Vacation" -> EventType.Vacation
                            "Seminar" -> EventType.Seminar
                            "BusinessTrip" -> EventType.BusinessTrip
                            else -> throw IllegalArgumentException("Unknown leaveType: ${jsonObject.getString("leaveType")}")
                        }
                        events.add(
                            Event(
                                id = jsonObject.getString("employeeId"),
                                timestamp = jsonObject.getString("date"),
                                leave_type = eventType,
                                comment = jsonObject.getString("comment")
                            )
                        )
                    }
                    onResult(Result.success(events))
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