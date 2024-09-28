package com.nextbigthing.thepunchline.ui.screens

sealed class Screen(val route:String) {
    data object DashboardScreen:Screen("mainScreen")
    data object JokesScreen:Screen("detailScreen")

    fun withArgs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}