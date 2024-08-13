package com.rehabilitationpro.network

fun sendEmployeeRegistration(
    employeeId: String,
    employeeName: String,
    employeePassword: String,
    position: String,
    employeePhoneNumber: String,
    joiningDate: String,
    onResult: (Result<String>) -> Unit
) {
    val url = "http://192.168.45.240:8080/employees"
    val jsonData = """
        {
          "employeeId": "$employeeId",
          "employeeName": "$employeeName",
          "employeePassword": "$employeePassword",
          "employeePhoneNumber": "$employeePhoneNumber",
          "position": "$position",
          "joiningDate": "$joiningDate"
        }
    """.trimIndent()

    HttpClient.sendPostRequest(url, jsonData, onResult)
}