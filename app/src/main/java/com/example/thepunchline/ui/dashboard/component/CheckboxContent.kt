package com.example.thepunchline.ui.dashboard.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxDialogContent(checkboxes: List<String>, checkedStates: List<Boolean>, onCheckboxChange: (Int, Boolean) -> Unit) {
    Column {
        checkboxes.forEachIndexed { index, label ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checkedStates[index],
                    onCheckedChange = { onCheckboxChange(index, it) },
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = label)
            }
        }
    }
}