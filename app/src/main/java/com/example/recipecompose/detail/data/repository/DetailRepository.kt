package com.example.recipecompose.detail.data.repository

import com.example.recipecompose.detail.data.model.DetailDataLayer
import com.example.recipecompose.detail.data.network.dto.RecipedesDetailDto

interface DetailRepository {

    suspend fun fetchRecipeDetail(id: Int): Result<DetailDataLayer>

}