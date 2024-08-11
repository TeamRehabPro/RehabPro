package com.rehabilitationpro.screens.signin.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = ColorPalette.borderGray,
            thickness = 1.dp
        )
        Text(
            text = "OR",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = ColorPalette.textGray
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = ColorPalette.borderGray,
            thickness = 1.dp
        )
    }
}