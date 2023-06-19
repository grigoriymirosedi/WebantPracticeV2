package com.example.webantpracticev2.data.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.webantpracticev2.data.db.PostDatabase
import com.example.webantpracticev2.data.db.PostListItemLocal
import com.example.webantpracticev2.data.remote.api.PostsApi
import com.example.webantpracticev2.data.remote.api.PostsClient
import com.example.webantpracticev2.data.remote.dto.PostListDto
import com.example.webantpracticev2.data.remote.dto.PostListItem
import com.example.webantpracticev2.domain.repository.PostsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Response

class PostListRepositoryImpl(
    val database: PostDatabase
) : PostsRepository {
    fun getPosts(): List<PostListItem> {
        return database.getPostDao().getAllPosts()
    }

    suspend fun refreshPosts(postPage: Int) {
        withContext(Dispatchers.IO) {
            val response = getPosts(postPage)
            if (response.isSuccessful) {
                response.body().let { responseResult ->
                    if (responseResult != null) {
                        var responseData = responseResult.data
                        var list = ArrayList<PostListItem>()
                        for (i in 0..(responseData.size-1)) {
                            list.add(responseData[i])
                        }
                        database.getPostDao().insertItem(list)
                    }
                }
            }
        }
    }

    override suspend fun getPosts(pageNumber: Int): Response<PostListDto> =
        PostsClient.providePostsApi().getPostsList(pageNumber)
}