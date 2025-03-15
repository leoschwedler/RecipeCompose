package com.example.recipecompose.search.data.network.api

import com.example.recipecompose.search.data.network.dto.SearchQueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("recipes/complexSearch")
    suspend fun fetchSearchQuery(@Query("query") query: String): Response<SearchQueryResponse>

}