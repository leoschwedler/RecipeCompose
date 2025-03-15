package com.example.recipecompose.detail.data.datasource

import com.example.recipecompose.detail.data.network.dto.RecipedesDetailDto

interface DetailRemoteDataSource {

    suspend fun fetchRecipeDetail(id: Int) : RecipedesDetailDto
}