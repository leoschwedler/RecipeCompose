package com.example.recipecompose.search.data.di

import com.example.recipecompose.search.data.datasource.SearchRemoteDataSourceImpl
import com.example.recipecompose.search.data.repository.SearchRepository
import com.example.recipecompose.search.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchRepositoryModule {


    @Binds
    @Singleton
    abstract fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}