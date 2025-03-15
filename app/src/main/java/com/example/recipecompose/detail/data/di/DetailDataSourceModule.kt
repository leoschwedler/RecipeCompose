package com.example.recipecompose.detail.data.di

import com.example.recipecompose.detail.data.datasource.DetailRemoteDataSource
import com.example.recipecompose.detail.data.datasource.DetailRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindDetailRemoteDataSource(detailRemoteDataSourceImpl: DetailRemoteDataSourceImpl): DetailRemoteDataSource
}