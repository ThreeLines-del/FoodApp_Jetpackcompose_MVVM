package com.linesapp.easyfood.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SearchScreen(
    viewModel: MealViewModel,
    navController: NavController
){
    val searchText = viewModel.searchText.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val meal by viewModel.meal.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = searchText.value,
            onValueChange = { newValue ->
                viewModel.setSearchText(newValue)
                viewModel.performSearch()
            },
            placeholder = {
                Text(
                    text = "search meal"
                )
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search icon")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (isSearching) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(16.dp)
                )
            }
        }else{

            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
            ){
                items(meal.size){ index ->
                    val item = meal[index]
                    if (item != null) {
                        SearchItem(
                            meal = item,
                            onItemClick = {
                                navController.navigate(
                                    "Detail_Screen"
                                )
                                viewModel.mutableStateMeal.value = item
                            }
                        )
                    }
                }
            }
        }
    }
}