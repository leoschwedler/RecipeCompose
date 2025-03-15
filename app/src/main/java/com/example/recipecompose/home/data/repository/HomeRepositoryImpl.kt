package com.example.recipecompose.home.data.repository

import android.accounts.NetworkErrorException
import androidx.compose.runtime.internal.illegalDecoyCallException
import com.example.recipecompose.home.data.datasource.HomeRemoteDataSource
import com.example.recipecompose.home.data.model.HomeDataLayer

import com.example.recipecompose.home.data.remote.network.api.HomeService
import com.example.recipecompose.home.data.remote.network.dto.RecipesDTO
import com.example.recipecompose.home.data.remote.network.dto.toHomeDataLayer
import java.net.UnknownHostException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val homeRemoteDataSource: HomeRemoteDataSource) : HomeRepository {
    override suspend fun fetchRecipeRandom(): Result<List<HomeDataLayer>> {
       return try {
           val response = homeRemoteDataSource.fetchRecipeRandom()
           val dataLayer = response.recipes.map { it.toHomeDataLayer() }
           return Result.success(dataLayer)
       }catch (e: Exception){
           Result.failure(e)
       }
    }
}