package com.example.recipecompose.home.data.repository

import com.example.recipecompose.home.data.model.HomeDataLayer
import com.example.recipecompose.home.data.remote.network.dto.RecipesDTO


interface HomeRepository {

    suspend fun fetchRecipeRandom(): Result<List<HomeDataLayer>>

}