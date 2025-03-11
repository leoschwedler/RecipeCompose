package com.example.recipecompose.search.data.dto

import com.example.recipecompose.search.presentation.model.SearchUiData

data class SearchQueryDTO(
    val id: Int,
    val title: String,
    val image: String,
)

fun SearchQueryDTO.toSearchUiData(): SearchUiData{
   return SearchUiData(
        id = id,
        title = title,
        image = image
    )
}
