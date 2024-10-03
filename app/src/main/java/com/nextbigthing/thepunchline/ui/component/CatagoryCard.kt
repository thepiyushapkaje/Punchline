package com.nextbigthing.thepunchline.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nextbigthing.thepunchline.ui.theme.AppBackgroundColor
import com.nextbigthing.thepunchline.ui.theme.KanitBlack

@Composable
fun CenteredTextCard(text: String, drawable: Int, onClick: () -> Unit) {

    val configuration = LocalConfiguration.current
    val screenWidthPx = (configuration.screenWidthDp / 2) - 10
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(screenWidthPx.dp)
            .padding(6.dp)
            .clickable { onClick() }
            // Set background color
            .background(AppBackgroundColor)
            // Apply rounded corners after background
            .clip(shape = RoundedCornerShape(18.dp)),
        elevation = CardDefaults.cardElevation(),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = drawable),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(50.dp) // Adjust size as needed
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontFamily = KanitBlack,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp) // Adjust padding as needed
                    .background(Color.White)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
        }
    }
}