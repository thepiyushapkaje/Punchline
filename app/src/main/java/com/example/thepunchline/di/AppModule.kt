package com.example.thepunchline.di

import com.example.thepunchline.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun retrofit(): Retrofit{
        return Retrofit.Builder().baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun apiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}
