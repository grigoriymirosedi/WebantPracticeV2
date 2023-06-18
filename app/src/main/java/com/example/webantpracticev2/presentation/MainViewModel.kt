package com.example.webantpracticev2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.webantpracticev2.commons.Resource
import com.example.webantpracticev2.data.remote.api.PostsClient
import com.example.webantpracticev2.data.remote.dto.PostListDto
import com.example.webantpracticev2.data.repository.PostListRepositoryImpl
import com.example.webantpracticev2.domain.repository.PostsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel: ViewModel() {

    val postRepository: PostsRepository = PostListRepositoryImpl()

    private val _postsData: MutableLiveData<Resource<PostListDto>> = MutableLiveData()
    var mService = PostsClient.providePostsApi()
    val postsData: LiveData<Resource<PostListDto>>
        get() = _postsData

    var postPage = 1

    init {
        getPosts()
    }
    fun getPosts() = viewModelScope.launch {
        _postsData.postValue(Resource.Loading())
        val response = postRepository.getPosts(postPage)
        _postsData.postValue(handleResponse(response))
    }

    fun handleResponse(response: Response<PostListDto>): Resource<PostListDto> {
        if(response.isSuccessful) {
            response.body()?.let { responseResult ->
                return Resource.Success(responseResult)
            }
        }
        return Resource.Error(response.message())
    }

    /*init {
        mService.getPostsList().enqueue(object : Callback<PostListDto> {
            override fun onResponse(
                call: Call<PostListDto>,
                response: Response<PostListDto>
            ) {
//                postsAdapter = PostsAdapter(response.body()!!.data)
//                binding.postsRv.adapter = postsAdapter
                _postsData.value = response.body()?.data
            }

            override fun onFailure(call: Call<PostListDto>, t: Throwable) {
            }
        })
    }*/
}