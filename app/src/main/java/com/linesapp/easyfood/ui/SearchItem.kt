package com.linesapp.easyfood.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linesapp.easyfood.model.random_meal.Meal

@Composable
fun SearchItem(
    meal: Meal,
    onItemClick: ()-> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(5.dp)
            .clickable(
                onClick = onItemClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .weight(2f)
                .clip(RoundedCornerShape(5.dp))
        ){
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                model = meal.strMealThumb,
                contentDescription = "meal image",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        meal.strMeal?.let {
            Text(
                modifier = Modifier
                    .weight(3f),
                text = it,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}