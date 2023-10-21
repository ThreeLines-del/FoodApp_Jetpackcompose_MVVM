package com.linesapp.easyfood.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linesapp.easyfood.R
import com.linesapp.easyfood.model.random_meal.Meal
import com.linesapp.easyfood.ui.theme.accent
import com.linesapp.easyfood.ui.theme.primary

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun DetailScreen(
    viewModel: MealViewModel
){
    val meal = viewModel.mutableStateMeal.value

    var isExpanded by remember { mutableStateOf(false) }

    val scrollState =  rememberScrollState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable {
                    isExpanded = !isExpanded
                }
        ){

            if (meal != null) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = meal.strMealThumb,
                    contentDescription = "Meal Image",
                    contentScale = ContentScale.Crop
                )
            }

            if (meal != null) {
                meal.strMeal?.let {
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 20.dp, bottom = 20.dp),
                        text = it,
                        fontSize = 30.sp,
                        color = Color.White
                    )
                }
            }
        }

        AnimatedVisibility(isExpanded){
            Column(
                modifier = Modifier
                    .verticalScroll(state = scrollState, enabled = true)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "-instructions: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                if (meal != null) {
                    meal.strInstructions?.let {
                        Text(
                            text = it,
                            maxLines = Int.MAX_VALUE,
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp),
                painter = painterResource(id = R.drawable.baseline_category_24),
                contentDescription = "Category",
                tint = Color.Gray
            )
            if (meal != null) {
                Text(
                    text = "Category: " + meal.strCategory,
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(50.dp))

            Icon(
                modifier = Modifier
                    .size(30.dp),
                imageVector = Icons.Default.Place,
                contentDescription = "Area",
                tint = Color.Gray
            )
            if (meal != null) {
                Text(
                    text = "Area: " + meal.strArea,
                    color = Color.Gray,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            FloatingActionButton(
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f),
                onClick = {
                    if (meal != null) {
                        viewModel.upsert(meal)
                        Toast.makeText(context, "Saved to Favorites", Toast.LENGTH_LONG).show()
                    }
                },
                contentColor = accent
            ) {
                Icon(
                    modifier = Modifier
                        .size(50.dp),
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = "favorite"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "tap image to see instructions",
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(90.dp)
                    .clickable {
                               val intent = Intent(Intent.ACTION_VIEW, Uri.parse(meal?.strYoutube ?: ""))

                        if(intent.resolveActivity(context.packageManager) != null){
                            context.startActivity(intent)
                        }else{
                            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(meal?.strYoutube ?: ""))
                            context.startActivity(webIntent)
                        }
                    },
                painter = painterResource(id = R.drawable.youtube96),
                contentDescription = "Youtube",
                tint = Color.Unspecified
            )
        }
    }
}