package com.example.recipecompose.detail.data.repository


import com.example.recipecompose.detail.data.datasource.DetailRemoteDataSource
import com.example.recipecompose.detail.data.model.DetailDataLayer
import com.example.recipecompose.detail.data.network.dto.toDetaiDataLayer
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val detailRemoteDataSource: DetailRemoteDataSource) :
    DetailRepository {
    override suspend fun fetchRecipeDetail(id: Int): Result<DetailDataLayer> {
        return try {
            val response = detailRemoteDataSource.fetchRecipeDetail(id = id)
            val detailDataLayer = response.toDetaiDataLayer()
            return Result.success(detailDataLayer)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}