package com.example.thepunchline.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thepunchline.ui.catgories.MainScreen
import com.example.thepunchline.ui.jokes.JokesScreen
import com.example.thepunchline.ui.screens.Screen
import com.example.thepunchline.util.JokesPreferenceHelper
import com.example.thepunchline.viewModel.JokesViewModel

@Composable
fun Navigation(viewModel: JokesViewModel, jokesPreferenceHelper: JokesPreferenceHelper) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.DashboardScreen.route) {
        composable(route = Screen.DashboardScreen.route) {
            MainScreen(navController = navController, jokesPreferenceHelper = jokesPreferenceHelper)
        }
        composable(
            route = Screen.JokesScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "Philipp"
                nullable = true
            })
        ) { entry ->
            entry.arguments?.getString("name")
                ?.let { JokesScreen(category = it, jokesPreferenceHelper = jokesPreferenceHelper, viewModel = viewModel) }
        }
    }
}



