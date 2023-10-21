package com.linesapp.easyfood.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MealByCategoryScreen(
    viewModel: MealViewModel
){
    val categoryString = viewModel.mutableStateCategoryString.value
    val mealStateCategory = viewModel.mealByCategory(categoryString).collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "$categoryString: ${mealStateCategory.value?.body()?.meals?.size}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(count = 3),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp), // top and bottom margin between each item
            horizontalArrangement = Arrangement.spacedBy(space = 16.dp), // left and right margin between each item
            contentPadding = PaddingValues(10.dp), // margin for the whole layout
        ) {
            val item = mealStateCategory.value?.body()?.meals

            if (item != null) {
                items(item.size){ index ->
                    val meal = item[index]

                    MealByCategoryListItem(mealCategory = meal)
                }
            }
        }
    }
}