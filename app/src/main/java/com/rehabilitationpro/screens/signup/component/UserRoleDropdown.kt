package com.rehabilitationpro.screens.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rehabilitationpro.ui.theme.ColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRoleDropdown(
    selectedRole: MutableState<String?>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val roles = listOf("Manager", "Employee")

    // 선택된 역할 유무에 따라 border 색상을 결정
    val borderColor = if (selectedRole.value == null) ColorPalette.borderGray else ColorPalette.signInBlue

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
            .fillMaxWidth(0.8f)
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .menuAnchor()
                .fillMaxSize()
                .background(ColorPalette.inputBoxGray, shape = RoundedCornerShape(12.dp))
                .border(1.dp, borderColor, shape = RoundedCornerShape(12.dp))  // 동적으로 변경되는 border 색상
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "User Role",
                tint = ColorPalette.textGray,
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = selectedRole.value ?: "Choose your role",
                color = if (selectedRole.value == null) ColorPalette.textGray else ColorPalette.myBlack,
                modifier = Modifier.weight(1f)
            )
            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
        }

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            roles.forEach { role ->
                DropdownMenuItem(
                    text = { Text(text = role) },
                    onClick = {
                        selectedRole.value = role
                        expanded = false
                    }
                )
            }
        }
    }
}
