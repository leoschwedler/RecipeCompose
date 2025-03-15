package com.example.recipecompose.search.data.datasource

import com.example.recipecompose.search.data.network.api.SearchService
import com.example.recipecompose.search.data.network.dto.SearchQueryResponse
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(private val service: SearchService): SearchRemoteDataSource {
    override suspend fun fetchSearch(query: String): SearchQueryResponse {
        val response = service.fetchSearchQuery(query = query)
        if (response.isSuccessful){
            return response.body() ?: throw Exception("Response body is null")
        }else{
            throw Exception("Error fetch API: ${response.code()}")
        }
    }
}