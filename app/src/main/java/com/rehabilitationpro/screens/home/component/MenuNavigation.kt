package com.rehabilitationpro.screens.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rehabilitationpro.R
import com.rehabilitationpro.Screen
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun MenuNavigation(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MenuNavItem(
            title = "NOTICE",
            iconRes = R.drawable.icon_notice,
            navRoute = Screen.NoticeHome.route,
            navController = navController,
            modifier = Modifier.weight(1f)
        )
        MenuNavItem(
            title = "My CN",
            iconRes = R.drawable.icon_calendar,
            navRoute = Screen.Reservation.route,
            navController = navController,
            modifier = Modifier.weight(1f)
        )
        MenuNavItem(
            title = "MY DIARY",
            iconRes = R.drawable.icon_diary,
            navRoute = Screen.Schedule.route,
            navController = navController,
            modifier = Modifier.weight(1f)
        )
        MenuNavItem(
            title = "SETTING",
            iconRes = R.drawable.icon_setting,
            navRoute = Screen.Setting.route,
            navController = navController,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MenuNavItem(
    title: String,
    iconRes: Int,
    navRoute: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(horizontal = 8.dp)
            .clickable { navController.navigate(navRoute) }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .size(80.dp)
                .background((ColorPalette.inputBoxGray), shape = RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = title,
                modifier = Modifier
                    .size(32.dp)
            )
        }
        Text(
            text = title,
            color = Color.Black,
            fontSize = 12.sp,
        )
    }
}
