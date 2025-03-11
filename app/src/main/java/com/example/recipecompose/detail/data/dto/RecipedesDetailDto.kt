package com.example.recipecompose.detail.data.dto

import com.example.recipecompose.detail.presentation.model.DetailUiData

data class RecipedesDetailDto(
    val image: String,
    val title: String,
    val summary: String
)

fun RecipedesDetailDto.toDetailUiData(): DetailUiData{
    return  DetailUiData(
        image = image,
        title = title,
        summary = summary
    )
}
