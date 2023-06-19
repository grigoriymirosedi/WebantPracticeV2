package com.example.webantpracticev2.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.webantpracticev2.data.remote.dto.PostListItem

@Dao
interface PostListItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: List<PostListItem>)

    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<PostListItem>
}