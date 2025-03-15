package com.example.recipecompose.detail.data.di

import com.example.recipecompose.detail.data.network.api.DetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DetailNetworkModule {

    @Provides
    @Singleton
    fun provideServiceDetail(retrofit: Retrofit): DetailService {
        return retrofit.create(DetailService::class.java)
    }


}