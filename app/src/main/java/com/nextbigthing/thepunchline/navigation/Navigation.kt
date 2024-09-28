package com.nextbigthing.thepunchline.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nextbigthing.thepunchline.ui.catgories.MainScreen
import com.nextbigthing.thepunchline.ui.jokes.JokesScreen
import com.nextbigthing.thepunchline.ui.screens.Screen
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import com.nextbigthing.thepunchline.viewModel.JokesViewModel

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
                ?.let { JokesScreen(navController = navController, category = it, jokesPreferenceHelper = jokesPreferenceHelper, viewModel = viewModel) }
        }
    }
}



