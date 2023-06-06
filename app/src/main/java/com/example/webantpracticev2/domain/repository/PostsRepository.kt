package com.example.webantpracticev2.domain.repository

import com.example.webantpracticev2.data.remote.dto.PostListDto
import retrofit2.Call

interface PostsRepository {

    fun getPosts(): Call<PostListDto>
}