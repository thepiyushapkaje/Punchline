package com.example.thepunchline.api

import com.example.thepunchline.data.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/joke/{category}")
    fun getJokesFromApi(@Path("category") category: String): Call<Jokes>

}