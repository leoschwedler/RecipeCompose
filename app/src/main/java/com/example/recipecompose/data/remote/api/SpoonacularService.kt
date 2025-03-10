package com.example.recipecompose.data.remote.api

import com.example.recipecompose.data.remote.dto.RecipeRandomResponse
import com.example.recipecompose.data.remote.dto.RecipedesDetailDto
import com.example.recipecompose.data.remote.dto.SearchQueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularService {

    @GET("recipes/random?limitLicense=true&tags=&number=10")
    suspend fun getRecipeRandom(): Response<RecipeRandomResponse>

    @GET("recipes/{id}/information?includeNutrition=false")
    suspend fun getRecipeDetail(@Path("id") id: Int): Response<RecipedesDetailDto>

    @GET("recipes/complexSearch")
    suspend fun getSearchQuery(@Query("query") query: String): Response<SearchQueryResponse>

}