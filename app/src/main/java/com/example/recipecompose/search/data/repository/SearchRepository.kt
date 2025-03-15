package com.example.recipecompose.search.data.repository

import com.example.recipecompose.search.data.model.SearchDataLayer
import com.example.recipecompose.search.data.network.dto.SearchQueryDTO

interface SearchRepository {

    suspend fun fetchSearch(query: String): Result<List<SearchDataLayer>>
}