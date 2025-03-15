package com.example.recipecompose.detail.data.di

import com.example.recipecompose.detail.data.repository.DetailRepository
import com.example.recipecompose.detail.data.repository.DetailRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DetailRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDetailResository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}