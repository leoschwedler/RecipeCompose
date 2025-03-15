package com.example.recipecompose.search.data.di

import com.example.recipecompose.search.data.network.api.SearchService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchNetworkModule {

    @Provides
    @Singleton
    fun provideServiceSearch(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }
}