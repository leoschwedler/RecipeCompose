package com.example.recipecompose.detail.data.repository

import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.detail.data.dto.RecipedesDetailDto

interface DetailRepository {

    suspend fun fetchRecipeDetail(id: Int): Result<RecipedesDetailDto>

}