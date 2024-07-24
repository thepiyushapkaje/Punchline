package com.example.thepunchline.ui.dashboard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepunchline.ui.theme.KanitBlack

@Composable
fun CenteredCard(title: String, description: String, onNextArticleClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically, false),
                    fontSize = if (title.length > 60) 24.sp else if (title.length>40) 34.sp else 44.sp,
                    lineHeight = if (title.length > 60) 24.sp else if (title.length>40) 34.sp else 44.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = KanitBlack,
                    softWrap = true
                )
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = if (title.length > 60) 18.sp else if (title.length>40) 28.sp else 38.sp,
                    lineHeight = if (title.length > 60) 18.sp else if (title.length>40) 28.sp else 38.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = KanitBlack,
                    color = Color.Gray,
                    softWrap = true
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = onNextArticleClick,
            modifier = Modifier
                .width(200.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text("Read Another Joke",
                fontFamily = KanitBlack
            )
        }
    }
}