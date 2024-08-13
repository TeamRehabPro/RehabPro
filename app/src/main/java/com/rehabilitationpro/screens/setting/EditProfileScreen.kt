package com.rehabilitationpro.screens.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rehabilitationpro.Screen
import com.rehabilitationpro.widgets.EditProfileScreenHeader
import com.rehabilitationpro.widgets.NameInputField
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.ui.theme.RehabPROTheme
import com.rehabilitationpro.widgets.AddressInputField
import com.rehabilitationpro.widgets.DateOfBirthInputField
import com.rehabilitationpro.widgets.RoleInputFieldFalse
import com.rehabilitationpro.widgets.SignUpScreenHeader


@Composable
fun EditProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EditProfileScreenHeader(onBackClick = { navController.navigate(Screen.Profile.route) })
        Spacer(modifier = Modifier.height(48.dp))

        val userName = remember { mutableStateOf("냉냉면") }
        NameInputField(fieldValue = userName)
        Spacer(modifier = Modifier.height(16.dp))

        val position = remember { mutableStateOf("Manager") }
        RoleInputFieldFalse(fieldValue = position)
        Spacer(modifier = Modifier.height(16.dp))

        val dateOfBirth = remember { mutableStateOf("1970-01-01") }
        DateOfBirthInputField(fieldValue = dateOfBirth)
        Spacer(modifier = Modifier.height(16.dp))

        val address = remember { mutableStateOf("Unknown") }
        AddressInputField(fieldValue = address)
        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { /*TODO*/ }) {
            Text("Save Changes")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    val navController = rememberNavController()
    RehabPROTheme {
        EditProfileScreen(navController = navController)
    }
}