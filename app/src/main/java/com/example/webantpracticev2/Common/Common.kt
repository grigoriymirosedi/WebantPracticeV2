package com.example.webantpracticev2.Common

import com.example.webantpracticev2.`interface`.RetrofitServices
import com.example.webantpracticev2.retrofit.RetrofitClient
import retrofit2.Retrofit

object Common {

    private val BASE_URL = "https://gorest.co.in/public-api/posts/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}