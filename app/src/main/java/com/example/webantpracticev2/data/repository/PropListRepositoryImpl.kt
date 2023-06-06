package com.example.webantpracticev2.data.repository

import com.example.webantpracticev2.data.remote.api.PostsApi
import com.example.webantpracticev2.data.remote.dto.PostListDto
import com.example.webantpracticev2.domain.repository.PostsRepository
import retrofit2.Call

class PropListRepositoryImpl(
    private val api: PostsApi
): PostsRepository {
    override fun getPosts(): Call<PostListDto> {
        return api.getPostsList()
    }
}