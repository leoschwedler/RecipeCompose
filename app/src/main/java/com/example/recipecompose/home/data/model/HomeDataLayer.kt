package com.example.recipecompose.home.data.model

import com.example.recipecompose.home.presentation.model.HomeUiData

data class HomeDataLayer(
    val id: Int,
    val image: String,
    val title: String,
)

fun HomeDataLayer.toHomeUiData() : HomeUiData{
    return HomeUiData(
        id = id,
        image = image,
        title = title
    )
}
