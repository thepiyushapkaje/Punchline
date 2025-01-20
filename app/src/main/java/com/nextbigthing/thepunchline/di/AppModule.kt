package com.nextbigthing.thepunchline.di

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nextbigthing.thepunchline.api.ApiService
import com.nextbigthing.thepunchline.api.NewApiService
import com.nextbigthing.thepunchline.util.AppConstant
import com.nextbigthing.thepunchline.util.JokesPreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
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
    @Named("OldRetrofit")
    fun retrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun apiService(@Named("OldRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Named("NewRetrofit")
    fun newRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jokes-always.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader("x-rapidapi-host", "jokes-always.p.rapidapi.com")
                            .addHeader("x-rapidapi-key", "84a94507famsh31eca89c1f145a6p15ff2bjsn9602c0ecf733")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .build()
    }

    @Provides
    fun newApiService(@Named("NewRetrofit") newRetrofit: Retrofit): NewApiService {
        return newRetrofit.create(NewApiService::class.java)
    }

    @Provides
    fun firebaseInstance(): DatabaseReference {
        return FirebaseDatabase.getInstance().getReference("AppData")
    }
}
