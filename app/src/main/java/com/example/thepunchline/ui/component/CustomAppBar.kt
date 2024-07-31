package com.example.thepunchline.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepunchline.R
import com.example.thepunchline.ui.theme.AppBackgroundColor
import com.example.thepunchline.ui.theme.KanitBlack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(title: String, onBackClick: () -> Unit) {
    TopAppBar(

        title = {
            Row(
                Modifier.fillMaxWidth().padding(0.dp,0.dp,20.dp,0.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontFamily = KanitBlack,
                    color = Color.Black,
                    fontSize = 34.sp
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.leftarrow),
                    tint = Color.Black,
                    contentDescription = "Back",
                    modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = AppBackgroundColor
        )
    )
}