package com.example.recipecompose.search.data.network.dto

import com.example.recipecompose.search.data.model.SearchDataLayer
import com.example.recipecompose.search.presentation.model.SearchUiData

data class SearchQueryDTO(
    val id: Int,
    val title: String,
    val image: String,
)

fun SearchQueryDTO.toSearchDataLayer(): SearchDataLayer {
   return SearchDataLayer(
        id = id,
        title = title,
        image = image
    )
}
