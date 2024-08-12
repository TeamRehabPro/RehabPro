// utils.kt
package com.rehabilitationpro.screens.qrscanner

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

// 출근 시간 09:00:00 로 지정
fun getShiftStartTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 9)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar.timeInMillis
}

// 퇴근 시간 18:00:00 로 지정
fun getShiftEndTime(): Long {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.HOUR_OF_DAY, 18)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    return calendar.timeInMillis
}

private val client = OkHttpClient()
private val url = "http://192.168.45.240:8080/attendance"
fun sendAttendanceRequest(clockInTime: String?, clockOutTime: String?, onResult: (String) -> Unit) {
    val employeeId = "001"
    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    val json = """
        {
          "employeeId": "$employeeId",
          "date": "$date",
          "checkInTime": "${clockInTime ?: ""}",
          "checkOutTime": "${clockOutTime ?: ""}",
          "status": "Present"
        }
    """.trimIndent()

    val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

    val request = Request.Builder()
        .url(url)
        .post(body)
        .build()

    client.newCall(request).enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            e.printStackTrace()
            onResult("Failed to send request: ${e.message}")
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            response.use {
                if (it.isSuccessful) {
                    val responseData = it.body?.string()
                    onResult(responseData ?: "No response from server")
                } else {
                    onResult("Request failed with code: ${it.code}")
                }
            }
        }
    })
}
