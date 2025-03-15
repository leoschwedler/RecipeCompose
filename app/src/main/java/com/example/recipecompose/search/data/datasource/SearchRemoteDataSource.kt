package com.example.recipecompose.search.data.datasource

import com.example.recipecompose.search.data.network.dto.SearchQueryResponse

interface SearchRemoteDataSource {

    suspend fun fetchSearch(query: String) : SearchQueryResponse
}