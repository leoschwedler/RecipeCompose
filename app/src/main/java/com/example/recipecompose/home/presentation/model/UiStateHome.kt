package com.example.recipecompose.home.presentation.model

import com.example.recipecompose.home.data.remote.dto.RecipesDTO

data class UiStateHome(
    val isLoading: Boolean = false,
    val isError: String = "",
    val listRecipeRandom: List<RecipesDTO> = emptyList(),
    var query: String = "",
)
