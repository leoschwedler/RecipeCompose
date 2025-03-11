package com.example.recipecompose.search.data.repository

import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.search.data.api.SearchService
import com.example.recipecompose.search.data.dto.SearchQueryDTO
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(val service: SearchService) : SearchRepository {
    override suspend fun fetchSearch(query: String): Result<List<SearchQueryDTO>> {
        return try {
            val response = service.getSearchQuery(query = query)
            if (response.isSuccessful){
                response.body()?.results?.let { Result.Success(it) } ?: Result.Error("Empty response from server")
            }else{
                Result.Error("Request failed - Code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
            }
        }catch (e: Exception){
            Result.Error("Exception: $e")
        }
    }
}