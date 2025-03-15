package com.example.recipecompose.detail.data.datasource

import com.example.recipecompose.detail.data.network.api.DetailService
import com.example.recipecompose.detail.data.network.dto.RecipedesDetailDto
import javax.inject.Inject

class DetailRemoteDataSourceImpl @Inject constructor(private val service: DetailService) : DetailRemoteDataSource {
    override suspend fun fetchRecipeDetail( id: Int): RecipedesDetailDto {
        val response = service.getRecipeDetail(id = id)
        if (response.isSuccessful){
            return response.body() ?: throw Exception("Response body is null")
        }else{
            throw Exception("Error fetch API: ${response.code()}")
        }
    }
}