package com.linesapp.easyfood.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CategoryScreen(
    viewModel: MealViewModel,
    navController: NavController
){
    val categoryItemState = viewModel.getCategoryList().collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(count = 3),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp), // top and bottom margin between each item
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp), // left and right margin between each item
            contentPadding = PaddingValues(end = 10.dp), // margin for the whole layout
            userScrollEnabled = false
        ) {
            val items = categoryItemState.value?.body()?.categories
            if (items != null) {
                items(items.size) { index ->
                    val category = items[index]
                    CategoryListItem(
                        category = category,
                        onClick = {
                            navController.navigate(
                                "Meal_By_Category_Screen"
                            )
                            viewModel.mutableStateCategoryString.value = category.strCategory
                        }
                    )
                }
            }
        }
    }
}