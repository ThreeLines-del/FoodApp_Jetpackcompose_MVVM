package com.linesapp.easyfood.repository

import androidx.compose.runtime.MutableState
import com.linesapp.easyfood.api.RetrofitInstance
import com.linesapp.easyfood.database.MealDao
import com.linesapp.easyfood.database.MealDatabase
import com.linesapp.easyfood.model.random_meal.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MealRepository @Inject constructor(
    private val mealDatabase: MealDatabase
){
    suspend fun getRandomMeal() =
        RetrofitInstance.api.getRandomMeal()

    suspend fun getMostPopularMeal(categoryName: String) =
        RetrofitInstance.api.getPopularMeals(categoryName)

    suspend fun getCategoryList() =
        RetrofitInstance.api.getCategoryList()

    suspend fun mealByCategory(categoryName: String) =
        RetrofitInstance.api.mealByCategory(categoryName)

    suspend fun upsertMeal(meal: Meal) =
        mealDatabase.dao().upsertMeal(meal)

    suspend fun deleteMeal(meal: Meal) =
        mealDatabase.dao().deleteMeal(meal)

    val getAllMeals: Flow<List<Meal>> = mealDatabase.dao().getAllMeals()

    suspend fun searchMeal(searchQuery: String) =
        RetrofitInstance.api.searchMeal(searchQuery)
}