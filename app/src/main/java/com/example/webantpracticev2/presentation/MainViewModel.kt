package com.example.webantpracticev2.presentation

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webantpracticev2.commons.Resource
import com.example.webantpracticev2.data.db.PostDatabase
import com.example.webantpracticev2.data.remote.api.PostsClient
import com.example.webantpracticev2.data.remote.dto.PostListDto
import com.example.webantpracticev2.data.remote.dto.PostListItem
import com.example.webantpracticev2.data.repository.PostListRepositoryImpl
import com.example.webantpracticev2.domain.repository.PostsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    application: Application,
): AndroidViewModel(application) {
    private val database = PostDatabase.invoke(context = application.applicationContext)
    val postRepository = PostListRepositoryImpl(database)
    var postListResponse: PostListDto? = null

    private val _localPostsData: MutableLiveData<List<PostListItem>> = MutableLiveData()
    private val localPostsData: LiveData<List<PostListItem>>
        get() = _localPostsData

    private val _postsData: MutableLiveData<Resource<PostListDto>> = MutableLiveData()
    val postsData: LiveData<Resource<PostListDto>>
        get() = _postsData

    var postPage = 1

    init {
        getPosts()
    }


    fun getPosts() = viewModelScope.launch {
        _postsData.postValue(Resource.Loading())
        val response = postRepository.getPosts(postPage)
        postRepository.refreshPosts(postPage)
        _postsData.postValue(handleResponse(response))
    }

    fun handleResponse(response: Response<PostListDto>): Resource<PostListDto> {
        if(response.isSuccessful) {
            response.body()?.let { responseResult ->
                postPage++
                if(postListResponse == null) {
                    postListResponse = responseResult
                } else {
                    val oldData = postListResponse?.data
                    val newData = responseResult.data
                    oldData?.addAll(newData)
                }
                return Resource.Success(postListResponse ?: responseResult)
            }
        }
        return Resource.Error(response.message())
    }
}