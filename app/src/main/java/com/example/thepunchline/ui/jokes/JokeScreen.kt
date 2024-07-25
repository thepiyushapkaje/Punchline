package com.example.thepunchline.ui.jokes

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.thepunchline.ui.component.CenteredCard
import com.example.thepunchline.ui.theme.AppBackgroundColor
import com.example.thepunchline.util.JokesPreferenceHelper
import com.example.thepunchline.viewModel.JokesViewModel

@Composable
fun JokesScreen(category: String, jokesPreferenceHelper: JokesPreferenceHelper, viewModel: JokesViewModel) {
    // Fetch jokes when the category changes
    val flags = jokesPreferenceHelper.getString("JOKES_FLAGS", "")
    LaunchedEffect(category) {
        viewModel.fetchJokes(category, flags)
    }

    val jokesResponse by viewModel.jokesResponse.observeAsState()

    jokesResponse?.let { response ->
        val title = response.setup ?: response.joke
        val description = response.delivery ?: ""
        Surface(color = AppBackgroundColor) {
            if (title != null) {
                CenteredCard(
                    title = title,
                    description = description,
                    onNextArticleClick = { viewModel.fetchJokes(category, flags) }
                )
            }
        }
    }
}