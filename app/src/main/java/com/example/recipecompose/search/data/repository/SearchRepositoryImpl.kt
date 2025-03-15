package com.example.recipecompose.search.data.repository

import com.example.recipecompose.search.data.datasource.SearchRemoteDataSource
import com.example.recipecompose.search.data.model.SearchDataLayer
import com.example.recipecompose.search.data.network.dto.toSearchDataLayer
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource) :
    SearchRepository {
    override suspend fun fetchSearch(query: String): Result<List<SearchDataLayer>> {
        return try {
            val response = searchRemoteDataSource.fetchSearch(query = query)
            val searchDataLayer = response.results.map { it.toSearchDataLayer() }
            return Result.success(searchDataLayer)
        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}