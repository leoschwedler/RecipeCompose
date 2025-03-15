package com.example.recipecompose.home.data.di

import com.example.recipecompose.home.data.repository.HomeRepository
import com.example.recipecompose.home.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface HomeRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository( homeRepositoryImpl: HomeRepositoryImpl): HomeRepository
}