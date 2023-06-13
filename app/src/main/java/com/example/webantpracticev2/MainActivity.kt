package com.example.webantpracticev2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        binding.postsRv.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.postsData.observe(this) {
            binding.postsRv.adapter = PostsAdapter(it)
        }
    }
}