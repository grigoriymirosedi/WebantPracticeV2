package com.example.webantpracticev2.data.remote.api

import com.example.webantpracticev2.data.remote.dto.PostListDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {
    @GET("public-api/posts")
    suspend fun getPostsList(
        @Query("page")
        pageNumber: Int = 1
    ): Response<PostListDto> // Replace with Response<PostListDto> ???
}