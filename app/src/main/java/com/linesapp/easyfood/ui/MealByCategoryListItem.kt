package com.linesapp.easyfood.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linesapp.easyfood.R
import com.linesapp.easyfood.model.random_meal.MealX

@Composable
fun MealByCategoryListItem(
    mealCategory: MealX
){
    Column(
        modifier = Modifier
            .height(200.dp)
            .width(150.dp)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
        ) {
            AsyncImage(
                model = mealCategory.strMealThumb,
                contentDescription = "category image",
                contentScale = ContentScale.Fit
            )
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = mealCategory.strMeal,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}