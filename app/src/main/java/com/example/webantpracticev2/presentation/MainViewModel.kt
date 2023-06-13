package com.example.webantpracticev2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.webantpracticev2.data.remote.api.PostsClient
import com.example.webantpracticev2.data.remote.dto.PostListDto
import com.example.webantpracticev2.data.remote.dto.PostListItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _postsData = MutableLiveData<List<PostListItem>>()
    var mService = PostsClient.providePostsApi()
    val postsData: LiveData<List<PostListItem>>
        get() = _postsData

    init {
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
    }
}