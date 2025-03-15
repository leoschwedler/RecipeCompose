package com.example.recipecompose.home.data.di

import com.example.recipecompose.home.data.datasource.HomeRemoteDataSource
import com.example.recipecompose.home.data.datasource.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource
}