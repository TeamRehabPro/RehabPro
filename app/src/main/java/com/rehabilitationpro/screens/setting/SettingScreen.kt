package com.rehabilitationpro.screens.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rehabilitationpro.R
import com.rehabilitationpro.Screen
import com.rehabilitationpro.screens.setting.component.ProfileSection
import com.rehabilitationpro.ui.theme.ColorPalette
import com.rehabilitationpro.ui.theme.RehabPROTheme
import com.rehabilitationpro.widgets.ProfileEditButton
import com.rehabilitationpro.widgets.SettingScreenHeader
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    val userName = mutableStateOf("냉냉면")
    val position = mutableStateOf("Manager")
    val dateOfBirth = mutableStateOf("1970-01-01")
    val address = mutableStateOf("Unknown")

    fun updateProfile(newName: String, newPosition: String, newDateOfBirth: String, newAddress: String) {
        viewModelScope.launch {
            userName.value = newName
            position.value = newPosition
            dateOfBirth.value = newDateOfBirth
            address.value = newAddress
        }
    }
}

@Composable
fun SettingScreen(navController: NavHostController, profileViewModel: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // [1] 뒤로가기 버튼이 포함된 프로필 스크린 헤더
        SettingScreenHeader(onBackClick = { navController.navigate(Screen.Home.route) })

        // [2] 현재 프로필 및 수정 버튼
        ProfileSection()
        Spacer(modifier = Modifier.height(24.dp))

        // Q. 이건 왜 있지?
        Text(text = "Manager", color = ColorPalette.myGray)
        Spacer(modifier = Modifier.height(24.dp))

        // [3] 프로필 상세 정보
//        ProfileInformation()
        val profileViewModel: ProfileViewModel = viewModel()
        ProfileInformation(profileViewModel)

        //
        ProfileEditButton(onClick = { navController.navigate(Screen.Profile.route) })
    }
}

@Composable
fun ProfileInformation() {
    Column {
        ProfileLine(
            iconResId = R.drawable.baseline_person_24,
            describe = "Name",
            content = "Sushi Matcha Ramen"
        )
        ProfileLine(
            iconResId = R.drawable.baseline_shopping_bag_24,
            describe = "Role",
            content = "Manager"
        )
        ProfileLine(
            iconResId = R.drawable.baseline_calendar_month_24,
            describe = "Date of Birth",
            content = "23 October 2000"
        )
        ProfileLine(
            iconResId = R.drawable.baseline_house_24,
            describe = "Address",
            content = "Ji Duck Mortesa No.102, New York, USA"
        )
    }
}


@Composable
fun ProfileActionButtons() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Edit Profile")
    }

    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(ColorPalette.myWhite)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_logout_24),
                contentDescription = "Logout",
            )
            Text(text = " Logout", color = Color.Black)
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun ProfileLine(iconResId: Int, describe: String, content: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = describe
        )
        Text(text = describe)
    }
    Text(text = content, color = ColorPalette.textGray)
    HorizontalDivider(
        Modifier.padding(vertical = 4.dp),
        color = ColorPalette.borderGray)
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun ProfileInformation(viewModel: ProfileViewModel) {
    Column {
        ProfileLine(
            iconResId = R.drawable.baseline_person_24,
            describe = "Name",
            content = viewModel.userName.value
        )
        ProfileLine(
            iconResId = R.drawable.baseline_shopping_bag_24,
            describe = "Role",
            content = viewModel.position.value
        )
        ProfileLine(
            iconResId = R.drawable.baseline_calendar_month_24,
            describe = "Date of Birth",
            content = viewModel.dateOfBirth.value
        )
        ProfileLine(
            iconResId = R.drawable.baseline_house_24,
            describe = "Address",
            content = viewModel.address.value
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//fun SettingMainScreenPreview() {
//    val navController = rememberNavController()
//    RehabPROTheme { SettingScreen(
//        navController = navController,
//        profileViewModel = profileViewModel
//    ) }
//}
