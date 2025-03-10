package com.example.recipecompose.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.recipecompose.data.remote.api.SpoonacularService
import com.example.recipecompose.data.remote.dto.RecipesDTO
import com.example.recipecompose.presentation.components.CustomCard
import com.example.recipecompose.presentation.components.CustomSearch

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    service: SpoonacularService,
    modifier: Modifier = Modifier
) {
    var listRecipeRandom by remember { mutableStateOf<List<RecipesDTO>>(emptyList()) }
    var query by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        try {
            val response = service.getRecipeRandom()
            if (response.isSuccessful) {
                response.body()?.recipes?.let {
                    listRecipeRandom = it
                }
            } else {
                Log.e(
                    "HomeScreen",
                    "Error code: ${response.code()} - Error Body: ${response.errorBody()}"
                )
            }
        } catch (e: Exception) {
            Log.e("HomeScreen", "Exception $e")
        }
    }

    HomeContent(
        listrecipesDTO = listRecipeRandom,
        onClick = {
            navHostController.navigate(route = "DetailScreen/${it.id}")
        },
        modifier = modifier,
        query = query,
        changeQuery = {
            query = it
        },
        toggleSearchQuery = {
            navHostController.navigate("SearchScreen/$query")
        }

    )
}

@Composable
private fun HomeContent(
    listrecipesDTO: List<RecipesDTO>,
    query: String,
    changeQuery: (String) -> Unit,
    toggleSearchQuery: (String) -> Unit,
    onClick: (RecipesDTO) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(color = Color.Black) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                "Find best recipes\nfor cooking",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            CustomSearch(
                modifier = Modifier.padding(top =  15.dp),
                query = query,
                changeQuery = changeQuery,
                toggleSearchQuery = toggleSearchQuery
            )

            Text(
                "Recipes",
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 15.dp),
                color = Color.White
            )
            LazyColumn {
                items(listrecipesDTO) {
                    CustomCard(recipesDTO = it, onClick = onClick)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {

}