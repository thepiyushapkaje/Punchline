package com.nextbigthing.thepunchline.navigation

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nextbigthing.thepunchline.navigation.screens.Screen
import com.nextbigthing.thepunchline.ui.about.AboutScreen
import com.nextbigthing.thepunchline.ui.catgories.MainScreen
import com.nextbigthing.thepunchline.ui.jokes.JokesScreen
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import com.nextbigthing.thepunchline.viewModel.JokesViewModel
import com.nextbigthing.thepunchline.viewModel.NewJokesViewModel

@Composable
fun Navigation(viewModel: JokesViewModel, newViewModel: NewJokesViewModel,
               jokesPreferenceHelper: JokesPreferenceHelper, context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.DashboardScreen.route) {
        // Dashboard Screen
        composable(route = Screen.DashboardScreen.route) {
            // MainScreen content here
            MainScreen(navController = navController, jokesPreferenceHelper = jokesPreferenceHelper)

            // Handle back press on Dashboard to exit the app
            BackHandler {
                // Exit the app when back is pressed on Dashboard screen
                (context as? Activity)?.finish()
            }
        }
        
        composable(route = Screen.AboutScreen.route){
            AboutScreen(contextT = context, navController = navController)
        }

        // Jokes Screen with navigation argument
        composable(
            route = Screen.JokesScreen.route + "/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "Philipp"
                nullable = true
            })
        ) { entry ->
            entry.arguments?.getString("name")?.let {
                JokesScreen(
                    navController = navController,
                    category = it,
                    jokesPreferenceHelper = jokesPreferenceHelper,
                    viewModel = viewModel,
                    newViewModel = newViewModel,
                    context = context
                )
            }
        }
    }
}



