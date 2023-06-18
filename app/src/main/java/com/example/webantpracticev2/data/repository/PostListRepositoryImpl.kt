package com.example.webantpracticev2.data.repository

import com.example.webantpracticev2.data.db.PostDatabase
import com.example.webantpracticev2.data.remote.api.PostsApi
import com.example.webantpracticev2.data.remote.api.PostsClient
import com.example.webantpracticev2.data.remote.dto.PostListDto
import com.example.webantpracticev2.domain.repository.PostsRepository
import retrofit2.Call
import retrofit2.Response

class PostListRepositoryImpl(
    //val db: PostDatabase
): PostsRepository {
    override suspend fun getPosts(pageNumber: Int): Response<PostListDto> =
        PostsClient.providePostsApi().getPostsList(pageNumber)
}