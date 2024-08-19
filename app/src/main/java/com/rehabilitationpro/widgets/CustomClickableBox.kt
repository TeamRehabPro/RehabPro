package com.rehabilitationpro.widgets

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.R
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun SignInWithGoogle() {
    val context = LocalContext.current
    CustomClickableBox(
        iconResId = R.drawable.icon_google,
        text = "Sign in with Google",
        onClick = { /*TODO : Social Login with Google*/
            Toast.makeText(context, "Social Login with Google : TO BE UPDATED", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun SignInWithFacebook() {
    val context = LocalContext.current
    CustomClickableBox(
        iconResId = R.drawable.icon_facebook,
        text = "Sign in with Facebook",
        onClick = { /*TODO : Social Login with Facebook*/
            Toast.makeText(context, " Social Login with Facebook : TO BE UPDATED", Toast.LENGTH_SHORT).show()
        }
    )
}

/**
 * A composable function that creates a custom sign-up box for various platforms.
 *
 * @param iconResId The icon representing the platform (e.g., Google, Facebook).
 * @param text The text to be displayed next to the icon.
 * @param onClick A lambda function to be executed when the box is clicked.
 * @param modifier A Modifier to be applied to the sign-up box. Default is Modifier.
 */
@Composable
fun CustomClickableBox(
    iconResId: Int,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(56.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .border(1.dp, ColorPalette.borderGray, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Color.Unspecified,  // SVG 파일의 원래 색상을 유지하기 위해 Unspecified 로 설정
            modifier = Modifier
                .size(24.dp)
                .padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = ColorPalette.myBlack,
            modifier = Modifier.weight(1f)
        )
    }
}