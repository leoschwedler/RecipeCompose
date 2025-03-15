package com.example.recipecompose.search.data.di

import com.example.recipecompose.search.data.datasource.SearchRemoteDataSource
import com.example.recipecompose.search.data.datasource.SearchRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindSearchRemoteDataSource( searchRemoteDataSourceImpl: SearchRemoteDataSourceImpl) : SearchRemoteDataSource
}