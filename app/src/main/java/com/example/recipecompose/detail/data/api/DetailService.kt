package com.example.recipecompose.detail.data.api

import com.example.recipecompose.detail.data.dto.RecipedesDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("recipes/{id}/information?includeNutrition=false")
    suspend fun getRecipeDetail(@Path("id") id: Int): Response<RecipedesDetailDto>
}