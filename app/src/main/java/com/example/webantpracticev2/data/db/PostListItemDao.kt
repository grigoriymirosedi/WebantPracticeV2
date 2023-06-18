package com.example.webantpracticev2.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostListItemDao {

    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<PostListItemLocal>>
}