package com.nextbigthing.thepunchline.util

import com.nextbigthing.thepunchline.R

object AppConstant {
    val jokesList = listOf("Miscellaneous", "Programming", "Engineer", "Doctor", "Dark", "Pun", "Spooky", "Christmas")
    val jokesResList = listOf(
        R.drawable.misc,
        R.drawable.programming,
        R.drawable.engineer,
        R.drawable.doctor,
        R.drawable.dark,
        R.drawable.pun,
        R.drawable.spooky,
        R.drawable.xmas
    )
    const val VERSION_CODE = "versionCode"
    const val VERSION_NAME = "versionName"
    const val BASE_URL = "https://v2.jokeapi.dev/"
    const val BASE_URL_NEW = "https://jokes-always.p.rapidapi.com/"
    const val APP_PACKAGE_NAME = "com.nextbigthing.thepunchline"
}