package com.linesapp.easyfood.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linesapp.easyfood.R
import com.linesapp.easyfood.model.random_meal.Category

@Composable
fun CategoryListItem(
    category: Category,
    onClick: () -> Unit
){
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(IntrinsicSize.Max)
            .background(Color.White)
            .clickable(
                onClick = onClick
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = "category image",
                contentScale = ContentScale.Fit
            )
        }

        Text(
            modifier = Modifier
                .weight(1f),
            text = category.strCategory,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Composable
//@Preview
//fun CategoryListItemPreview(){
//    CategoryListItem()
//}