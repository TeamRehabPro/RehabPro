package com.rehabilitationpro.screens.profile

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rehabilitationpro.R
import com.rehabilitationpro.ui.theme.ColorPalette
import com.rehabilitationpro.ui.theme.RehabPROTheme

class ProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RehabPROTheme {
                Profile()
            }
        }
    }
}

@Composable
fun Profile() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 헤드 필요합니다
        Box(
            modifier = Modifier
                .size(128.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "profileImage",
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_edit_square_24),
                    contentDescription = "edit",
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(8.dp)
                        .clickable { TODO() }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Manager", color = ColorPalette.myGray)
        Spacer(modifier = Modifier.height(48.dp))
        Column(
            modifier = Modifier.weight(1f),
        ) {
            ProfileLine(
                img = R.drawable.baseline_person_24,
                desc = "Name",
                content = "Sushi Matcha Ramen"
            )
            ProfileLine(
                img = R.drawable.baseline_shopping_bag_24,
                desc = "Role",
                content = "Manager"
            )
            ProfileLine(
                img = R.drawable.baseline_calendar_month_24,
                desc = "Date of Birth",
                content = "23 October 2000"
            )
            ProfileLine(
                img = R.drawable.baseline_house_24,
                desc = "Address",
                content = "Ji Duck Mortesa No.102, New York, USA"
            )
        }
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Edit Profile")
        }
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(),
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
}

@Composable
fun ProfileLine(img: Int, desc: String, content: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Image(
            painter = painterResource(id = img),
            contentDescription = desc
        )
        Text(text = desc)
    }
    Text(text = content, color = ColorPalette.textGray)
    HorizontalDivider(Modifier.padding(vertical = 16.dp))
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    RehabPROTheme {
        Profile()
    }
}