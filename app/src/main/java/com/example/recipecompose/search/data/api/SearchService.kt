package com.example.recipecompose.search.data.api

import com.example.recipecompose.search.data.dto.SearchQueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("recipes/complexSearch")
    suspend fun getSearchQuery(@Query("query") query: String): Response<SearchQueryResponse>

}