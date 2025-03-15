package com.example.recipecompose.search.data.model

import com.example.recipecompose.search.presentation.model.SearchUiData

data class SearchDataLayer(
    val id: Int,
    val title: String,
    val image: String,
)

fun SearchDataLayer.toSearchUiData(): SearchUiData{
    return SearchUiData(
        id = id,
        title = title,
        image = image
    )
}
