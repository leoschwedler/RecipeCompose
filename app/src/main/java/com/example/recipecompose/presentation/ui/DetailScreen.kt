package com.example.recipecompose.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.recipecompose.data.remote.api.SpoonacularService
import com.example.recipecompose.data.remote.dto.RecipedesDetailDto


@Composable
fun DetailScreen(
    id: Int,
    service: SpoonacularService,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    var recipesDTO by remember { mutableStateOf<RecipedesDetailDto?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = service.getRecipeDetail(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    recipesDTO = it
                }
            } else {
                Log.e(
                    "DetailScreen",
                    "Error code: ${response.code()} - Error Body: ${response.errorBody()}"
                )
            }
        } catch (e: Exception) {
            Log.e("DetailScreen", "Exception $e")
        }
    }

    recipesDTO?.let {
        DetailContent(recipesDTO = it, onBackPressed = {
            navHostController.popBackStack()
        }, modifier = modifier)
    }
}

@Composable
private fun DetailContent(
    recipesDTO: RecipedesDetailDto,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = Color.Black) {
        Column(modifier = modifier.fillMaxSize() ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.Close, contentDescription = null, tint = Color.White)
                }
                Spacer(Modifier.width(5.dp))
                Text(
                    text = recipesDTO.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1
                )
            }
            AsyncImage(
                model = recipesDTO.image,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            )
            Text(text = recipesDTO.summary, fontSize = 12.sp, color = Color.White, maxLines = 5)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailPreview() {

}