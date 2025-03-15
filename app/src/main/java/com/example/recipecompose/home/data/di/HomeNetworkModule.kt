package com.example.recipecompose.home.data.di

import com.example.recipecompose.home.data.remote.network.api.HomeService
import com.example.recipecompose.home.data.repository.HomeRepository
import com.example.recipecompose.home.data.repository.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeNetworkModule {

    @Provides
    @Singleton
    fun provideServiceHome(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

}