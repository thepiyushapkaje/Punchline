package com.nextbigthing.thepunchline.navigation.screens

sealed class Screen(val route: String) {
    data object DashboardScreen : Screen("mainScreen")
    data object JokesScreen : Screen("detailScreen")
    data object AboutScreen : Screen("aboutScreen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}