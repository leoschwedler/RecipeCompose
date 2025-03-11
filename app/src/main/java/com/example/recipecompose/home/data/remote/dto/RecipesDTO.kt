package com.example.recipecompose.home.data.remote.dto

import com.example.recipecompose.home.presentation.model.HomeUiData

data class RecipesDTO(
    val id: Int,
    val image: String,
    val title: String,
)

fun RecipesDTO.toHomeUiData(): HomeUiData{
    return HomeUiData(
        id = id,
        image = image,
        title = title
    )
}
