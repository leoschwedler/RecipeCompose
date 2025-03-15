package com.example.recipecompose.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.recipecompose.commom.components.CustomCard
import com.example.recipecompose.commom.components.CustomSearch
import com.example.recipecompose.home.data.remote.network.dto.RecipesDTO
import com.example.recipecompose.home.presentation.model.HomeUiData
import com.example.recipecompose.home.presentation.model.HomeUiState
import com.example.recipecompose.home.presentation.viewmodel.HomeViewmodel

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewmodel: HomeViewmodel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewmodel.uiState.collectAsState()

    HomeContent(
        homeUiState = uiState,
        onClick = {
            navHostController.navigate(route = "DetailScreen/${it.id}")
        },
        modifier = modifier,
        changeQuery = viewmodel::onChangeQuery,
        toggleSearchQuery = {
            navHostController.navigate("SearchScreen/${uiState.query}")
        }

    )
}

@Composable
private fun HomeContent(
    homeUiState: HomeUiState,
    changeQuery: (String) -> Unit,
    toggleSearchQuery: (String) -> Unit,
    onClick: (HomeUiData) -> Unit,
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
                modifier = Modifier.padding(top = 15.dp),
                query = homeUiState.query,
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
            when{
                homeUiState.isError -> {
                    Text(text = homeUiState.errorMessage, color = Color.Red)
                }
                else -> {
                    LazyColumn {
                        items(homeUiState.listRecipeRandom) {
                            CustomCard(homeUiData = it, onClick = onClick)
                        }
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {

}