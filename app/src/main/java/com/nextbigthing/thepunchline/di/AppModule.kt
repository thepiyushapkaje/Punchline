package com.nextbigthing.thepunchline.di

import android.content.Context
import com.nextbigthing.thepunchline.api.ApiService
import com.nextbigthing.thepunchline.util.AppConstant
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun jokesPreferenceHelper(context: Context): JokesPreferenceHelper {
        return JokesPreferenceHelper(context)
    }

    @Provides
    fun retrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun apiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}
