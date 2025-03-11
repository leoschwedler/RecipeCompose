package com.example.recipecompose.search.data.repository

import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.search.data.dto.SearchQueryDTO

interface SearchRepository {

    suspend fun fetchSearch(query: String): Result<List<SearchQueryDTO>>
}