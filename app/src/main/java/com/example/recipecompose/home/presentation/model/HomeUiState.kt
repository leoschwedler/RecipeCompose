package com.example.recipecompose.home.presentation.model

data class HomeUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val listRecipeRandom: List<HomeUiData> = emptyList(),
    var query: String = "",
)

data class HomeUiData(
    val id: Int,
    val image: String,
    val title: String,
)
