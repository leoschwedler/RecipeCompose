package com.example.recipecompose.detail.presentation.model

import com.example.recipecompose.detail.data.dto.RecipedesDetailDto

data class DetailUiState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isError: Boolean = false,
    val detailDto: DetailUiData? = null
)

data class DetailUiData(
    val image: String,
    val title: String,
    val summary: String
)
