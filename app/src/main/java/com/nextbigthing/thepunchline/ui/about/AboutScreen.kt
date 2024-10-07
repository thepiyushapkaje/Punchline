package com.nextbigthing.thepunchline.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nextbigthing.thepunchline.R
import com.nextbigthing.thepunchline.ui.component.CustomAppBar
import com.nextbigthing.thepunchline.ui.theme.AppBackgroundColor
import com.nextbigthing.thepunchline.ui.theme.KanitBlack

@Composable
fun AboutScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Custom AppBar for the About Page
            CustomAppBar(
                title = "About",
                onBackClick = { navController.popBackStack() }
            )

            // App Icon (Replace with your actual image resource)
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 16.dp)
            )

            // App Name
            Text(
                text = "The Punchline",
                fontFamily = KanitBlack,
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 16.dp),
                fontWeight = FontWeight.Bold
            )

            // Brief Description
            Text(
                text = "The Punchline is your go-to app for quick, witty humor and entertainment.",
                textAlign = TextAlign.Center,
                fontFamily = KanitBlack,
                modifier = Modifier
                    .padding(18 .dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Disclaimer Text for Jokes
            Text(
                text = "Disclaimer: The jokes provided by The Punchline are intended for entertainment purposes only. Humor is subjective, and some jokes may not be suitable for all audiences. Please enjoy responsibly.",
                textAlign = TextAlign.Center,
                fontFamily = KanitBlack,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Privacy Policy Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(20.dp,0.dp,20.dp,0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(
                    "Privacy Policy",
                    fontFamily = KanitBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Review Button
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(20.dp,0.dp,20.dp,0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
            ) {
                Text(
                    "Leave a Review",
                    fontFamily = KanitBlack
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Version Info (Optional)
            Text(
                text = "Version 1.0.0",
                fontFamily = KanitBlack,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}
