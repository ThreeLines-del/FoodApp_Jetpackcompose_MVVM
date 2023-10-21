package com.linesapp.easyfood.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.linesapp.easyfood.ui.theme.accent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    viewModel: MealViewModel,
    navController: NavController
){
    LaunchedEffect(Unit){
        viewModel.getRandomFood()
    }

    val randomMealState = viewModel.mutableStateMeal
    val popularMealsState = viewModel.getMostPopularMeals("SeaFood").collectAsState(initial = null)
    val categoryItemState = viewModel.getCategoryList().collectAsState(initial = null)
    val scrollState = rememberScrollState()
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable{
        mutableStateOf(false)
    }

    if(isSheetOpen){
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            }
        ) {
            randomMealState.value?.let {
                BottomSheetItem(
                    meal = it,
                    onClick = {
                        navController.navigate(
                            "Detail_Screen"
                        )
                        isSheetOpen = false
                    }
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(start = 30.dp, top = 10.dp, end = 20.dp)
            .verticalScroll(scrollState, true)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                modifier = Modifier
                    .weight(3f),
                text = "Home",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )

            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.navigate(
                            "Search_Screen"
                        )
                    },
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon"
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 10.dp),
            text = "What would you like to eat?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box (
            modifier = Modifier
                .height(160.dp)
                .width(330.dp)
                .clip(RoundedCornerShape(10.dp))
                .combinedClickable(
                    onClick = {
                        navController.navigate(
                            "Detail_Screen"
                        )
                    },
                    onLongClick = {
                        isSheetOpen = true
                    }
                )
        ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = randomMealState.value?.strMealThumb,
                contentScale = ContentScale.Crop,
                contentDescription = "Random Food Image"
            )
        }

        Text(
            modifier = Modifier
                .padding(top = 18.dp),
            text = "Popular Items",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(end = 10.dp)
        ){
            val item = popularMealsState.value?.body()?.meals
            if (item != null) {
                items(item.size){ index ->
                    val meal = item[index]

                    PopularItem(
                        meal = meal,
                        onClick = {
                            navController.navigate(
                                "Detail_Screen"
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(top = 18.dp),
            text = "Categories",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            LazyVerticalGrid(
                modifier = Modifier.height(650.dp),
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
}