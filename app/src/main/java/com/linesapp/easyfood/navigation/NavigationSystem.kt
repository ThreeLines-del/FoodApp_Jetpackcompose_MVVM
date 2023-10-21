package com.linesapp.easyfood.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.linesapp.easyfood.model.random_meal.Meal
import com.linesapp.easyfood.ui.CategoryScreen
import com.linesapp.easyfood.ui.DetailScreen
import com.linesapp.easyfood.ui.FavoriteScreen
import com.linesapp.easyfood.ui.HomeScreen
import com.linesapp.easyfood.ui.MealByCategoryScreen
import com.linesapp.easyfood.ui.MealViewModel
import com.linesapp.easyfood.ui.SearchScreen

@Composable
fun NavigationSystem(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "route1"){

        navigation(
            startDestination = "Home_Screen",
            route = "route1"
        ){
            composable("Home_Screen"){ entry ->

                val viewModel = entry.mealViewModel<MealViewModel>(navController = navController)

                HomeScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable("Favorite_Screen"){ entry ->

                val viewModel = entry.mealViewModel<MealViewModel>(navController = navController)

                FavoriteScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable("Category_Screen"){ entry ->

                val viewModel = entry.mealViewModel<MealViewModel>(navController = navController)

                CategoryScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }

            composable("Detail_Screen"){ entry ->

                val viewModel = entry.mealViewModel<MealViewModel>(navController = navController)

                DetailScreen(
                    viewModel = viewModel
                )
            }

            composable("Meal_By_Category_Screen"){ entry ->

                val viewModel = entry.mealViewModel<MealViewModel>(navController = navController)

                MealByCategoryScreen(
                    viewModel = viewModel
                )
            }

            composable("Search_Screen"){ entry ->

                val viewModel = entry.mealViewModel<MealViewModel>(navController = navController)

                SearchScreen(
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.mealViewModel(
    navController: NavController,
): T {
    val navGraphRoute =destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}