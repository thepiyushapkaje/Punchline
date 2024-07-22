package com.example.thepunchline.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thepunchline.ui.jokes.JokesActivity
import com.example.thepunchline.ui.theme.BlagueTheme
import com.example.thepunchline.ui.theme.KanitBlack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            BlagueTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Column {
                        Row {
                            MyAppBar()
                        }
                        Row(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
                            val jokesList =
                                listOf(
                                    "Miscellaneous",
                                    "Programming",
                                    "Dark",
                                    "Pun",
                                    "Spooky",
                                    "Christmas"
                                )
                            CenteredTextGrid(items = jokesList) { clickedItem ->
                                val intent =
                                    Intent(this@DashBoardActivity, JokesActivity::class.java)
                                intent.putExtra("value", clickedItem)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppBar(
) {
    TopAppBar(
        title = { Text("The Punchline", fontFamily = KanitBlack) },
        actions = {
            IconButton(onClick = { /* Handle action icon click */ }) {
                Icon(Icons.Filled.FavoriteBorder, contentDescription = "Action Icon")
            }
        }
    )
}

@Preview
@Composable
fun Greeting() {
    BlagueTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black
        ) {
            Column {
                Row {
                    MyAppBar()
                }
                Row(modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
                    val jokesList =
                        listOf(
                            "Miscellaneous",
                            "Programming",
                            "Dark",
                            "Pun",
                            "Spooky",
                            "Christmas"
                        )
                    CenteredTextGrid(items = jokesList) { clickedItem ->

                    }
                }
            }
        }
    }
}

@Composable
fun CenteredTextGrid(items: List<String>, onCardClick: (String) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(items = items){
            CenteredTextCard(text = it) {
                onCardClick(it)
            }
        }
    }
}

@Composable
fun CenteredTextList(items: List<String>, onCardClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = items){
            CenteredTextCard(text = it) {
                onCardClick(it)
            }
        }
    }
}

@Composable
fun CenteredTextCard(text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(6.dp)
            .clickable { onClick() }
            .background(Color.Black)
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(randomGradient),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontFamily = KanitBlack,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}