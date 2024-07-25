package com.example.thepunchline.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CenteredTextGrid(items: List<String>, onCardClick: (String) -> Unit, drawableItems: List<Int>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(items.indices.toList()) { index ->
            CenteredTextCard(text = items[index], drawableItems[index]) {
                onCardClick(items[index])
            }
        }
    }
}

@Composable
fun CenteredTextList(items: List<String>, onCardClick: (String) -> Unit, drawableItems: List<Int>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items.indices.toList()) { index ->
            CenteredTextCard(text = items[index], drawableItems[index]) {
                onCardClick(items[index])
            }
        }
    }
}