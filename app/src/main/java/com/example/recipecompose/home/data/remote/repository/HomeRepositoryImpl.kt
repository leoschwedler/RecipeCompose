package com.example.recipecompose.home.data.remote.repository

import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.home.data.remote.api.HomeService
import com.example.recipecompose.home.data.remote.dto.RecipesDTO
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(val service: HomeService) : HomeRepository {
    override suspend fun fetchRecipeRandom(): Result<List<RecipesDTO>> {
        return try {
            val response = service.getRecipeRandom()
            if (response.isSuccessful) {
                response.body()?.recipes?.let { Result.Success(it) } ?: Result.Error("Empty response from server")
            } else {
                Result.Error("Request failed - Code: ${response.code()} - Error body: ${response.errorBody()?.toString()}")
            }
        } catch (e: Exception) {
            Result.Error("Exception: $e")
        }
    }
}