package com.example.recipecompose.detail.data.repository

import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.detail.data.api.DetailService
import com.example.recipecompose.detail.data.dto.RecipedesDetailDto
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(val service: DetailService): DetailRepository {
    override suspend fun fetchRecipeDetail(id: Int): Result<RecipedesDetailDto> {
        return try {
            val response = service.getRecipeDetail(id = id)
            if (response.isSuccessful){
                response.body()?.let { Result.Success(it) } ?: Result.Error("Empty response from server")
            }else{
                Result.Error("Request failed Code: ${response.code()} - Error body: ${response.errorBody()}")
            }
        }catch (e: Exception){
            Result.Error("Exception: $e")
        }
    }
}