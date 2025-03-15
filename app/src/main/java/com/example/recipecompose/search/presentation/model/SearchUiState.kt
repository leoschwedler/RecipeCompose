package com.example.recipecompose.search.presentation.model

import com.example.recipecompose.search.data.network.dto.SearchQueryDTO

data class SearchUiState(
    val listSearch: List<SearchUiData> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)

data class SearchUiData(
    val id: Int,
    val title: String,
    val image: String,
)
