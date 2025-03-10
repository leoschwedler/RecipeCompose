package com.example.recipecompose.di

import android.util.Log
import com.example.recipecompose.data.remote.api.SpoonacularService
import com.example.recipecompose.util.Constants
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
    fun providesRetrofi(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): SpoonacularService {
        return retrofit.create(SpoonacularService::class.java)
    }
}