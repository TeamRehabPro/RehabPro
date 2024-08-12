package com.rehabilitationpro.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rehabilitationpro.R

@Composable
fun SignInScreenHeader(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomScreenHeader(
        title = "Sign In",
        onLeftIconClick = onBackClick,
        leftIconResId = R.drawable.icon_back_arrow,
        modifier = modifier
    )
}

@Composable
fun SignUpScreenHeader(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CustomScreenHeader(
        title = "Create New Account",
        onLeftIconClick = onBackClick,
        leftIconResId = R.drawable.icon_back_arrow,
        modifier = modifier
    )
}

/**
 * A customizable screen header component with optional left and right icons, and a title.
 *
 * @param title The title text to be displayed in the center of the header.
 * @param onLeftIconClick A lambda function to be executed when the left icon is clicked. Null if no icon.
 * @param onRightIconClick A lambda function to be executed when the right icon is clicked. Null if no icon.
 * @param leftIconResId The resource ID of the drawable to be used for the left icon. Null if no icon.
 * @param rightIconResId The resource ID of the drawable to be used for the right icon. Null if no icon.
 * @param modifier Additional modifier for the header component.
 */
@Composable
fun CustomScreenHeader(
    title: String,
    onLeftIconClick: (() -> Unit)? = null,
    onRightIconClick: (() -> Unit)? = null,
    leftIconResId: Int? = null,
    rightIconResId: Int? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        leftIconResId?.let { iconResId ->
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "left icon",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(onClick = { onLeftIconClick?.invoke() })
                    .padding(start = 16.dp)
                    .size(24.dp)
            )
        }

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center
        )

        rightIconResId?.let { iconResId ->
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = "right icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = { onRightIconClick?.invoke() })
                    .padding(end = 16.dp)
                    .size(24.dp)
            )
        }
    }
}