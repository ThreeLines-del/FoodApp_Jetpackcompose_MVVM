package com.linesapp.easyfood.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.linesapp.easyfood.ui.theme.light_pink
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun FavoriteScreen(
    viewModel: MealViewModel,
    navController: NavController
){
    val savedMealList = viewModel.mealList.collectAsState(initial = null)

    val context = LocalContext.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            val items = savedMealList.value
            if (items != null) {
                items(items.size){ index ->
                    val meal = items[index]

                    val delete = SwipeAction(
                        onSwipe = {
                            viewModel.deleteMeal(meal)
                        },
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .padding(16.dp),
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        background = light_pink
                    )

                    SwipeableActionsBox(
                        startActions = listOf(delete),
                        endActions = listOf(delete),
                        swipeThreshold = 100.dp
                    ) {
                        SavedMealItem(
                            meal = meal,
                            onDeleteClick = {
                                viewModel.deleteMeal(meal)
                                Toast.makeText(context, " Meal Deleted", Toast.LENGTH_LONG).show()
                            },
                            onItemClick = {
                                navController.navigate(
                                    "Detail_Screen"
                                )
                                viewModel.mutableStateMeal.value = meal
                            }
                        )
                    }
                }
            }
        }

    }
}