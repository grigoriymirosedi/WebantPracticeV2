package com.example.webantpracticev2.`interface`

import com.example.webantpracticev2.models.PostListResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("?page=1&per_page=20")
    fun getPostsList(): Call<PostListResponse>
}