package com.example.thepunchline.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.thepunchline.navigation.Navigation
import com.example.thepunchline.ui.theme.ThePunchlineTheme
import com.example.thepunchline.util.JokesPreferenceHelper
import com.example.thepunchline.viewModel.JokesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var jokesPreferenceHelper: JokesPreferenceHelper
    private val viewModel:JokesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokesPreferenceHelper.clearPreferences()
        setContent {
            ThePunchlineTheme {
                Navigation(viewModel = viewModel, jokesPreferenceHelper = jokesPreferenceHelper)
            }
        }
    }
}