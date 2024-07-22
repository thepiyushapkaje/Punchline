package com.example.thepunchline.ui.jokes

import com.example.thepunchline.ui.dashboard.MyAppBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepunchline.ui.theme.BlagueTheme
import com.example.thepunchline.ui.theme.KanitBlack
import com.example.thepunchline.viewModel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesActivity : ComponentActivity() {

    private val viewModel: JokesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val category = intent.getStringExtra("value")
        category?.let { viewModel.fetchJokes(it) }

        viewModel.jokesResponse.observe(this) { response ->
            if (response != null) {
                val title = response.setup ?: response.joke
                val description = response.delivery ?: ""
                setContent {
                    BlagueTheme {
                        Surface(color = Color.Black) {
                            MyAppBar()
                            CenteredCard(
                                title = title,
                                description = description,
                                onNextArticleClick = { category?.let { viewModel.fetchJokes(it) } })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CenteredCard(title: String, description: String, onNextArticleClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            val startColor = Color(
                red = ((150..255)).random().toFloat() / 255.0f,
                green = ((150..255)).random().toFloat() / 255.0f,
                blue = ((150..255)).random().toFloat() / 255.0f,
                alpha = 1.0f
            )

            val endColor = Color(
                red = ((150..255)).random().toFloat() / 255.0f,
                green = ((150..255)).random().toFloat() / 255.0f,
                blue = ((150..255)).random().toFloat() / 255.0f,
                alpha = 1.0f
            )

            val randomGradient = Brush.linearGradient(
                colors = listOf(startColor, endColor)
            )
            Column(
                modifier = Modifier
                    .background(randomGradient)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.CenterVertically, false),
                    fontSize = if (title.length > 80) 24.sp else if (title.length>50) 34.sp else 44.sp,
                    lineHeight = if (title.length > 80) 24.sp else if (title.length>50) 34.sp else 44.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = KanitBlack,
                    softWrap = true
                )
                Text(
                    text = description,
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = if (title.length > 80) 18.sp else if (title.length>50) 28.sp else 38.sp,
                    lineHeight = if (title.length > 80) 18.sp else if (title.length>50) 28.sp else 38.sp,
                    textAlign = TextAlign.Left,
                    fontFamily = KanitBlack,
                    color = Color.White,
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
                contentColor = Color.Black // Set text color to black
            )
        ) {
            Text("Read Another Joke",
                fontFamily = KanitBlack)
        }
    }
}

@Preview
@Composable
fun showUI() {
    BlagueTheme {
        Surface(color = Color.Black) {
            CenteredCard(
                title = "Title",
                description = "description",
                onNextArticleClick = { })
        }
    }
}

