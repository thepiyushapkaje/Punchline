package com.nextbigthing.thepunchline.ui.jokes

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.nextbigthing.thepunchline.ui.component.CenteredCard
import com.nextbigthing.thepunchline.ui.component.CustomAppBar
import com.nextbigthing.thepunchline.ui.screens.Screen
import com.nextbigthing.thepunchline.ui.theme.AppBackgroundColor
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import com.nextbigthing.thepunchline.viewModel.JokesViewModel

@Composable
fun JokesScreen(
    navController: NavController,
    category: String,
    jokesPreferenceHelper: JokesPreferenceHelper,
    viewModel: JokesViewModel
) {
    // Fetch jokes when the category changes
    val flags = jokesPreferenceHelper.getString("JOKES_FLAGS", "")
    LaunchedEffect(category) {
        viewModel.fetchJokes(category, flags)
    }

    val jokesResponse by viewModel.jokesResponse.observeAsState()

    jokesResponse?.let { response ->
        val title = response.setup ?: response.joke
        val description = response.delivery ?: ""
        Surface(color = AppBackgroundColor){
            CustomAppBar(title = category, onBackClick = { navController.navigate(Screen.DashboardScreen.route) })
            CenteredCard(
                title = title,
                description = description,
                onNextArticleClick = { viewModel.fetchJokes(category, flags) }
            )
        }
    }
}