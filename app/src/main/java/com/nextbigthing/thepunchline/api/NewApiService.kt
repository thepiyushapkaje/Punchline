package com.nextbigthing.thepunchline.api

import com.nextbigthing.thepunchline.data.DoctorJokes
import com.nextbigthing.thepunchline.data.Jokes
import retrofit2.http.GET
import retrofit2.http.Path

interface NewApiService {

    @GET("{category}")
    suspend fun getDoctorJokes(@Path("category") category: String): DoctorJokes

}