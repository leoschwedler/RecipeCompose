package com.example.recipecompose.home.data.datasource

import com.example.recipecompose.home.data.remote.network.dto.RecipeRandomResponse

interface HomeRemoteDataSource {

    suspend fun fetchRecipeRandom() : RecipeRandomResponse
}