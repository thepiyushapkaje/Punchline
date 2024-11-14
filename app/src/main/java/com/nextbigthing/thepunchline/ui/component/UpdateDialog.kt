package com.nextbigthing.thepunchline.ui.component

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.nextbigthing.thepunchline.ui.theme.KanitBlack

@Composable
fun UpdateAppDialog(onUpdateClick: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        containerColor = Color.White,
        title = {
            Text(
                text = "New Version Available",
                fontFamily = KanitBlack
            )
        },
        text = {
            Text(
                "A new version of the app is available. Please update to enjoy the latest features and improvements.",
                fontFamily = KanitBlack
            )
        },
        confirmButton = {
            Button(
                onClick = { onUpdateClick() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text(
                    "Update",
                    fontFamily = KanitBlack
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    "Later",
                    fontFamily = KanitBlack,
                    color = Color.Black
                )
            }
        },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    )
}

