package com.example.recipecompose.home.data.remote.network.api

import com.example.recipecompose.home.data.remote.network.dto.RecipeRandomResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("recipes/random?limitLicense=true&tags=&number=10")
    suspend fun getRecipeRandom(): Response<RecipeRandomResponse>
}