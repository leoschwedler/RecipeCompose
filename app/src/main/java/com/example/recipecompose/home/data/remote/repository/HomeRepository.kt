package com.example.recipecompose.home.data.remote.repository

import com.example.recipecompose.commom.model.Result
import com.example.recipecompose.home.data.remote.dto.RecipesDTO

interface HomeRepository {

    suspend fun fetchRecipeRandom(): Result<List<RecipesDTO>>

}