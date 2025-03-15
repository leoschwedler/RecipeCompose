package com.example.recipecompose.detail.data.network.dto

import com.example.recipecompose.detail.data.model.DetailDataLayer
import com.example.recipecompose.detail.presentation.model.DetailUiData

data class RecipedesDetailDto(
    val image: String,
    val title: String,
    val summary: String
)

fun RecipedesDetailDto.toDetaiDataLayer(): DetailDataLayer{
    return  DetailDataLayer(
        image = image,
        title = title,
        summary = summary
    )
}
