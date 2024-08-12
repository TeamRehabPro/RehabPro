package com.rehabilitationpro.screens.signup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rehabilitationpro.ui.theme.ColorPalette

@Composable
fun TermsCheckbox(
    isTermsChecked: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    var showTermsDialog by remember { mutableStateOf(false) }
    var showPrivacyDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(start = 36.dp, end = 36.dp, bottom = 36.dp)
    ) {
        Checkbox(
            checked = isTermsChecked.value,
            onCheckedChange = { isTermsChecked.value = it },
            colors = CheckboxDefaults.colors(
                checkedColor = ColorPalette.signInBlue,
                uncheckedColor = ColorPalette.textGray,
                checkmarkColor = Color.White
            )
        )

        Text(
            text = buildAnnotatedString {
                append("I agree to the healthcare ")
                withStyle(style = SpanStyle(color = ColorPalette.signInBlue)) {
                    append("Terms of Service")
                }
                append(" and ")
                withStyle(style = SpanStyle(color = ColorPalette.signInBlue)) {
                    append("Privacy Policy")
                }
            },
            lineHeight = 14.sp,
            fontSize = 14.sp,
            modifier = Modifier.clickable {
                // 클릭한 텍스트에 따라 팝업을 다르게 띄우기
                if (showTermsDialog) {
                    showTermsDialog = true
                } else if (showPrivacyDialog) {
                    showPrivacyDialog = true
                }
            }
        )
    }

    // 팝업 : Terms of Service
    if (showTermsDialog) {
        AlertDialog(
            onDismissRequest = { showTermsDialog = false },
            title = { Text("Terms of Service") },
            text = { Text("Terms of Service 내용이 여기에 표시됩니다.") },
            confirmButton = {
                Button(onClick = { showTermsDialog = false }) {
                    Text("Close")
                }
            }
        )
    }

    // 팝업 : Privacy Policy
    if (showPrivacyDialog) {
        AlertDialog(
            onDismissRequest = { showPrivacyDialog = false },
            title = { Text("Privacy Policy") },
            text = { Text("Privacy Policy 내용이 여기에 표시됩니다.") },
            confirmButton = {
                Button(onClick = { showPrivacyDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}