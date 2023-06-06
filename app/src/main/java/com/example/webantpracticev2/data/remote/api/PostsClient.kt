package com.example.webantpracticev2.data.remote.api

import com.example.webantpracticev2.commons.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PostsClient {
    fun providePostsApi(): PostsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostsApi::class.java)
    }
}