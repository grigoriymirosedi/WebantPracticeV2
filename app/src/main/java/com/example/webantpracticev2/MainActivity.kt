package com.example.webantpracticev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webantpracticev2.Common.Common
import com.example.webantpracticev2.adapters.PostsAdapter
import com.example.webantpracticev2.databinding.ActivityMainBinding
import com.example.webantpracticev2.`interface`.RetrofitServices
import com.example.webantpracticev2.models.PostListItem
import com.example.webantpracticev2.models.PostListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mService: RetrofitServices
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var list: List<PostListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                //TODO: replace true with Handler?
//                true
//            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        mService = Common.retrofitService
        binding.postsRv.layoutManager = LinearLayoutManager(this)

        getAllPosts()
    }

    private fun getAllPosts() {
        mService.getPostsList().enqueue(object : Callback<PostListResponse> {
            override fun onResponse(
                call: Call<PostListResponse>,
                response: Response<PostListResponse>
            ) {
                postsAdapter = PostsAdapter(response.body()!!.data)
                binding.postsRv.adapter = postsAdapter
            }

            override fun onFailure(call: Call<PostListResponse>, t: Throwable) {
            }
        })
    }
}