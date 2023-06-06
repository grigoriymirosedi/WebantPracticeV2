package com.example.webantpracticev2.data.remote.api

import com.example.webantpracticev2.data.remote.dto.PostListDto
import retrofit2.Call
import retrofit2.http.GET

interface PostsApi {
    @GET("?page=1&per_page=20")
    fun getPostsList(): Call<PostListDto>
}