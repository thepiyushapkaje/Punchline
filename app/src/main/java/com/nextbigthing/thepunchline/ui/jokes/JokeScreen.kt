package com.nextbigthing.thepunchline.ui.jokes

import android.content.Context
import android.util.Log
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.navigation.NavController
import com.nextbigthing.thepunchline.navigation.screens.Screen
import com.nextbigthing.thepunchline.ui.component.CenteredCard
import com.nextbigthing.thepunchline.ui.component.CustomAppBar
import com.nextbigthing.thepunchline.ui.theme.AppBackgroundColor
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import com.nextbigthing.thepunchline.viewModel.JokesViewModel
import com.nextbigthing.thepunchline.viewModel.NewJokesViewModel

@Composable
fun JokesScreen(
    navController: NavController,
    category: String,
    jokesPreferenceHelper: JokesPreferenceHelper,
    viewModel: JokesViewModel,
    newViewModel: NewJokesViewModel,
    context: Context
) {
    // Fetch jokes when the category changes
    val flags = jokesPreferenceHelper.getString("JOKES_FLAGS", "")
    LaunchedEffect(category) {
        when (category) {
            "Doctor" -> {
                newViewModel.fetchDoctorJokes(category.lowercase())
            }
            "Engineer" -> {
                newViewModel.fetchDoctorJokes(category.lowercase())
            }
            else -> {
                viewModel.fetchJokes(category, flags)
            }
        }
    }


    val jokesResponse by viewModel.jokesResponse.observeAsState()
    val jokeResponse by newViewModel.doctorJokesResponse.observeAsState()

    if (category == "Doctor" || category == "Engineer"){
        jokeResponse.let {
            val title = it?.data
            Surface(color = AppBackgroundColor) {
                CustomAppBar(
                    title = category,
                    onBackClick = { navController.navigate(Screen.DashboardScreen.route) }
                )
                title?.let { it1 ->
                    CenteredCard(
                        title = it1,
                        description = "",
                        onNextArticleClick = { newViewModel.fetchDoctorJokes(category.lowercase()) },
                        context = context
                    )
                }
            }
        }
    }else{
        jokesResponse?.let { response ->
            val title = response.setup ?: response.joke
            val description = response.delivery ?: ""
            Surface(color = AppBackgroundColor) {
                CustomAppBar(
                    title = category,
                    onBackClick = { navController.navigate(Screen.DashboardScreen.route) }
                )
                CenteredCard(
                    title = title,
                    description = description,
                    onNextArticleClick = { viewModel.fetchJokes(category, flags) },
                    context = context
                )
            }
        } ?: run {
            Log.e("JokesScreen", "Jokes response is null.")
        }
    }

}