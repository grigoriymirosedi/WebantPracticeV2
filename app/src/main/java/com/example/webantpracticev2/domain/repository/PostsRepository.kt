package com.example.webantpracticev2.domain.repository

import com.example.webantpracticev2.data.remote.dto.PostListDto
import retrofit2.Call
import retrofit2.Response

interface PostsRepository {
    suspend fun getPosts(pageNumber: Int): Response<PostListDto>
}