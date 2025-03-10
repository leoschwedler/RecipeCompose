package com.example.recipecompose.search.presentation.model

import com.example.recipecompose.search.data.dto.SearchQueryDTO

data class UiStateSearch(
    val listSearch: List<SearchQueryDTO> = emptyList(),
    val isLoading: Boolean = false,
)
