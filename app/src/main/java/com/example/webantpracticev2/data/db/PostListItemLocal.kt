package com.example.webantpracticev2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.webantpracticev2.data.remote.dto.PostListItem

@Entity(tableName = "posts")
data class PostListItemLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val code: Int,
    val MetaData: Int,
    val data: List<PostListItem>,
)
