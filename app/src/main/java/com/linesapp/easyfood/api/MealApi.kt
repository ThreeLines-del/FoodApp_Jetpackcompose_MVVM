package com.linesapp.easyfood.api

import com.linesapp.easyfood.model.random_meal.CategoryResponse
import com.linesapp.easyfood.model.random_meal.MealCategoryResponse
import com.linesapp.easyfood.model.random_meal.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal(): Response<MealResponse>

    @GET("filter.php")
    suspend fun getPopularMeals(
        @Query("c")
        categoryName: String
    ): Response<CategoryResponse>

    @GET("categories.php")
    suspend fun getCategoryList(): Response<MealCategoryResponse>

    @GET("filter.php")
    suspend fun mealByCategory(
        @Query("c")
        categoryName: String
    ): Response<CategoryResponse>

    @GET("search.php")
    suspend fun searchMeal(
        @Query("s")
        searchQuery: String
    ): Response<MealResponse>
}