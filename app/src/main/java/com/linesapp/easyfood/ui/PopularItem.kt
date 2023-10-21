package com.linesapp.easyfood.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.linesapp.easyfood.R
import com.linesapp.easyfood.model.random_meal.MealX

@Composable
fun PopularItem(
    meal: MealX,
    onClick: () -> Unit
){
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable(
                onClick = onClick
            )
    ){
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = meal.strMealThumb,
            contentDescription = "popular meal image",
            contentScale = ContentScale.Crop
        )
    }
}