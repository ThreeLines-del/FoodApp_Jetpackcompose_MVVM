package com.linesapp.easyfood.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linesapp.easyfood.model.random_meal.CategoryResponse
import com.linesapp.easyfood.model.random_meal.Meal
import com.linesapp.easyfood.model.random_meal.MealCategoryResponse
import com.linesapp.easyfood.model.random_meal.MealX
import com.linesapp.easyfood.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val repository: MealRepository
): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _meal = MutableStateFlow(listOf<Meal?>())
    @OptIn(FlowPreview::class)
    val meal = searchText
        .debounce(2000)
        .combine(_meal){ text, city ->
            if(text.isBlank()){
                city
            }else{
                city.filter { it?.strMeal?.contains(text, ignoreCase = true) ?: false }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _meal.value
        )

    fun setSearchText(query: String){
        _searchText.value = query
    }

    fun performSearch(){
        viewModelScope.launch {
            val searchText = _searchText.value
            _isSearching.value = true
            try {
                val result = repository.searchMeal(searchText)
                _meal.value = result.body()?.meals ?: null!!
            }catch (e: Exception) {
                // Handle error
                Log.i("My Error", e.toString())
            } finally {
                _isSearching.value = false
            }
        }
    }

    var mutableStateMeal: MutableState<Meal?> = mutableStateOf(null)
    var mutableStateCategoryString: MutableState<String> = mutableStateOf("")

    val mealList: Flow<List<Meal>> = repository.getAllMeals

    init {
        mealByCategory(mutableStateCategoryString.value)
    }

    fun getRandomFood(){
        viewModelScope.launch {
            val result = repository.getRandomMeal().body()!!.meals[0]
            mutableStateMeal.value = result
        }
    }

    fun getMostPopularMeals(categoryName: String): Flow<Response<CategoryResponse>> = flow {
        val popularMeals = repository.getMostPopularMeal(categoryName)
        emit(popularMeals)
    }

    fun getCategoryList(): Flow<Response<MealCategoryResponse>> = flow {
        val category = repository.getCategoryList()
        emit(category)
    }

    fun mealByCategory(categoryName: String): Flow<Response<CategoryResponse>> = flow {
        val mealCategory = repository.mealByCategory(categoryName)
        emit(mealCategory)
    }

    fun upsert(meal: Meal) = viewModelScope.launch {
        repository.upsertMeal(meal)
    }

    fun deleteMeal(meal: Meal) = viewModelScope.launch {
        repository.deleteMeal(meal)
    }
}