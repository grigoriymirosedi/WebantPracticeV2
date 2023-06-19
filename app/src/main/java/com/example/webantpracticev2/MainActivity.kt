package com.example.webantpracticev2

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webantpracticev2.commons.Constants.QUERY_PAGE_SIZE
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
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { postListResponse ->
                        postsAdapter.differ.submitList(postListResponse.data.toList())
                        val totalPages = postListResponse.meta.pagination.total / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.postPage == totalPages
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
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
        isLoading = false
    }

    private fun showProgressBar() {
        binding.postPaginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }


    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isNotAtBeginning && isAtLastItem
                    && isScrolling && isTotalMoreThanVisible
            if (shouldPaginate) {
                viewModel.getPosts()
                isScrolling = false
            }
        }
    }

    private fun setupRecyclerView() {
        postsAdapter = PostsAdapter()
        binding.postsRv.layoutManager = LinearLayoutManager(this)
        binding.postsRv.adapter = postsAdapter
        binding.postsRv.addOnScrollListener(this@MainActivity.scrollListener)
    }
}