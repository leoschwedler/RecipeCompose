package com.example.recipecompose.home.data.remote.api

import com.example.recipecompose.home.data.remote.dto.RecipeRandomResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("recipes/random?limitLicense=true&tags=&number=10")
    suspend fun getRecipeRandom(): Response<RecipeRandomResponse>
}