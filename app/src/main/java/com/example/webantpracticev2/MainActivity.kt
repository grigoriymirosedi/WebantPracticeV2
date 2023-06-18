package com.example.webantpracticev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.webantpracticev2.commons.Resource
import com.example.webantpracticev2.presentation.PostsAdapter
import com.example.webantpracticev2.databinding.ActivityMainBinding
import com.example.webantpracticev2.data.remote.dto.PostListItem
import com.example.webantpracticev2.presentation.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //private lateinit var mService: PostsApi
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var list: List<PostListItem>
    private lateinit var splashScreen: SplashScreen

    val TAG: String = "PostResponse"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreen = installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.postsData.observe(this, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {postListResponse ->
                        postsAdapter.differ.submitList(postListResponse.data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
    private fun hideProgressBar() {
        binding.postPaginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.postPaginationProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter()
        binding.postsRv.layoutManager = LinearLayoutManager(this)
        binding.postsRv.adapter = postsAdapter
    }
}