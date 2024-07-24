package com.example.thepunchline.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thepunchline.R
import com.example.thepunchline.ui.dashboard.component.CenteredCard
import com.example.thepunchline.ui.dashboard.component.CenteredTextGrid
import com.example.thepunchline.ui.dashboard.component.CenteredTextList
import com.example.thepunchline.ui.dashboard.component.CheckboxDialogContent
import com.example.thepunchline.ui.screens.Screen
import com.example.thepunchline.ui.theme.AppBackgroundColor
import com.example.thepunchline.ui.theme.KanitBlack
import com.example.thepunchline.util.AppConstant
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, jokesPreferenceHelper: JokesPreferenceHelper) {
    var showListView by remember { mutableStateOf(false) }
    var showFilterView by remember { mutableStateOf(false) }
    val checkboxes = listOf("NSFW", "Religious", "Political", "Racist", "Sexist", "Explicit")
    val checkedStates = remember { mutableStateOf(List(checkboxes.size) { false }) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppBackgroundColor
    ) {
        Column {
            Row {
                TopAppBar(
                    title = { Text("The Punchline", fontFamily = KanitBlack) },
                    actions = {
                        IconButton(onClick = {
                            showListView = toggleShowListView(showListView)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.list_vector),
                                contentDescription = "Filter"
                            )
                        }
                        IconButton(onClick = {
                            showFilterView = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_filter_list),
                                contentDescription = "Filter"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = AppBackgroundColor
                    )
                )
            }
            Row {
                if (showListView) {
                    CenteredTextList(items = AppConstant.jokesList, onCardClick = {
                        navController.navigate(Screen.JokesScreen.withArgs(it))
                    }, drawableItems = AppConstant.jokesResList)
                } else {
                    CenteredTextGrid(items = AppConstant.jokesList, onCardClick = {
                        navController.navigate(Screen.JokesScreen.withArgs(it))
                    }, drawableItems = AppConstant.jokesResList)
                }
            }
        }
    }
    if (showFilterView) {
        AlertDialog(
            onDismissRequest = { showFilterView = false },
            text = {
                CheckboxDialogContent(checkboxes, checkedStates.value) { index, isChecked ->
                    checkedStates.value = checkedStates.value.toMutableList().apply {
                        this[index] = isChecked
                    }
                }
            }, modifier = Modifier.fillMaxWidth(),
            confirmButton = {
                Button(
                    onClick = {
                        val selectedIndices =
                            checkedStates.value.indices.filter { checkedStates.value[it] }
                        val selectedOptions = selectedIndices.map { checkboxes[it] }
                        jokesPreferenceHelper.saveString(
                            "JOKES_FLAGS",
                            selectedItemsFiltering(selectedOptions)
                        )
                        showFilterView = false
                    }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showFilterView = false }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}

private fun toggleShowListView(showListView: Boolean): Boolean {
    return !showListView
}

private fun selectedItemsFiltering(selectedOptions: List<String>): String {
    return selectedOptions.joinToString(",")
}

@Composable
fun JokesScreen(category: String, jokesPreferenceHelper: JokesPreferenceHelper, viewModel: JokesViewModel) {
    // Fetch jokes when the category changes
    val flags = jokesPreferenceHelper.getString("JOKES_FLAGS", "")
    LaunchedEffect(category) {
        viewModel.fetchJokes(category, flags)
    }

    val jokesResponse by viewModel.jokesResponse.observeAsState()

    jokesResponse?.let { response ->
        val title = response.setup ?: response.joke ?:""
        val description = response.delivery ?: ""
        Surface(color = AppBackgroundColor) {
            CenteredCard(
                title = title,
                description = description,
                onNextArticleClick = { viewModel.fetchJokes(category, flags) }
            )
        }
    }
}


