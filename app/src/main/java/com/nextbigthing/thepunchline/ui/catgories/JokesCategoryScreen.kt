package com.nextbigthing.thepunchline.ui.catgories

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.nextbigthing.thepunchline.R
import com.nextbigthing.thepunchline.ui.component.CenteredTextGrid
import com.nextbigthing.thepunchline.ui.component.CenteredTextList
import com.nextbigthing.thepunchline.ui.component.CheckboxDialogContent
import com.nextbigthing.thepunchline.ui.screens.Screen
import com.nextbigthing.thepunchline.ui.theme.AppBackgroundColor
import com.nextbigthing.thepunchline.ui.theme.KanitBlack
import com.nextbigthing.thepunchline.util.AppConstant
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper

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
                    title = { Text("The Punchline", fontFamily = KanitBlack, color = Color.Black) },
                    actions = {
                        IconButton(onClick = {
                            showListView = toggleShowListView(showListView)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.list_vector),
                                tint = Color.Black,
                                contentDescription = "Filter",
                            )
                        }
                        IconButton(onClick = {
                            showFilterView = true
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_filter_list),
                                tint = Color.Black,
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
            title = { Text(text = "Opt Out Categories", fontFamily = KanitBlack) },
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
                    Text(text = "Confirm", fontFamily = KanitBlack)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showFilterView = false }, colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Dismiss", fontFamily = KanitBlack)
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