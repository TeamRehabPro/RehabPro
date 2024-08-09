package com.rehabilitationpro.screens.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PositionDropdown(
    selectedPosition: String,
    onPositionSelected: (String) -> Unit,
    errorMessage: String? = null // Optional error message parameter
) {
    var expanded by remember { mutableStateOf(false) }
    val positions = listOf("Manager", "General")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedPosition,
            onValueChange = {},
            label = { Text("Position") },
            isError = errorMessage != null, // Set error state if there is an error message
            supportingText = { errorMessage?.let { Text(it) } }, // Show error message if present
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            positions.forEach { position ->
                DropdownMenuItem(
                    text = { Text(position) },
                    onClick = {
                        onPositionSelected(position) // Notify parent about the selected position
                        expanded = false
                    }
                )
            }
        }
    }
}
