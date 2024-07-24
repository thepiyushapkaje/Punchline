package com.example.thepunchline.ui.dashboard

import android.annotation.SuppressLint
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
class DashBoardActivity : ComponentActivity() {
    @Inject
    lateinit var jokesPreferenceHelper: JokesPreferenceHelper
    private val viewModel: JokesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThePunchlineTheme {
                Navigation(viewModel = viewModel, jokesPreferenceHelper = jokesPreferenceHelper)
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        jokesPreferenceHelper.clearPreferences()
    }
}