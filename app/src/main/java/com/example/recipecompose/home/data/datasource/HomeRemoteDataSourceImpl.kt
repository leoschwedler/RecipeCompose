package com.example.recipecompose.home.data.datasource

import com.example.recipecompose.home.data.remote.network.api.HomeService
import com.example.recipecompose.home.data.remote.network.dto.RecipeRandomResponse
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(private val service: HomeService): HomeRemoteDataSource {
    override suspend fun fetchRecipeRandom(): RecipeRandomResponse {
        val response = service.getRecipeRandom()
        if (response.isSuccessful){
           return response.body() ?: throw Exception("Response body is null")
        }else{
            throw Exception("Error API: ${response.code()}")
        }
    }
}