package com.nextbigthing.thepunchline.api

import com.nextbigthing.thepunchline.data.Jokes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("joke/{category}")
    fun getJokesFromApi(
        @Path("category") category: String,
        @Query("blacklistFlags") flags: String
    ): Call<Jokes>

}