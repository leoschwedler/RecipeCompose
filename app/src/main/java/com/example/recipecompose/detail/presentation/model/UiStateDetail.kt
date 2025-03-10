package com.example.recipecompose.detail.presentation.model

import com.example.recipecompose.detail.data.dto.RecipedesDetailDto

data class UiStateDetail(
    val isLoading: Boolean = false,
    val error: String = "",
    val detailDto: RecipedesDetailDto? = null
)
