package com.example.recipecompose.di

import android.util.Log
import com.example.recipecompose.commom.util.Constants
import com.example.recipecompose.search.data.network.api.SearchService
import com.example.recipecompose.detail.data.network.api.DetailService
import com.example.recipecompose.detail.data.repository.DetailRepository
import com.example.recipecompose.detail.data.repository.DetailRepositoryImpl
import com.example.recipecompose.home.data.remote.network.api.HomeService
import com.example.recipecompose.home.data.repository.HomeRepository
import com.example.recipecompose.home.data.repository.HomeRepositoryImpl
import com.example.recipecompose.search.data.repository.SearchRepository
import com.example.recipecompose.search.data.repository.SearchRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.d("API_LOG", message)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Provides
    @Singleton
    fun providesOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                chain.proceed(
                    request.newBuilder()
                        .url(
                            request.url.newBuilder().addQueryParameter("apiKey", Constants.TOKEN)
                                .build()
                        )
                        .build()
                )
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}