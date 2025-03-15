package com.example.recipecompose.home.data.remote.network.dto

import com.example.recipecompose.home.data.model.HomeDataLayer
import com.example.recipecompose.home.presentation.model.HomeUiData

data class RecipesDTO(
    val id: Int,
    val image: String,
    val title: String,
)

fun RecipesDTO.toHomeDataLayer(): HomeDataLayer {
    return HomeDataLayer(
        id = id,
        image = image,
        title = title
    )
}
