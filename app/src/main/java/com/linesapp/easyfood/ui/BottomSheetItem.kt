package com.linesapp.easyfood.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.linesapp.easyfood.R
import com.linesapp.easyfood.model.random_meal.Meal

@Composable
fun BottomSheetItem(
    meal: Meal,
    onClick: ()-> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(10.dp)
            .background(Color.White)
            .clickable(
                onClick = onClick
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            model = meal.strMealThumb,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            meal.strMeal?.let { Text(text = it) }
        }
    }
}

//@Composable
//@Preview
//fun Preview(){
//    BottomSheetItem()
//}