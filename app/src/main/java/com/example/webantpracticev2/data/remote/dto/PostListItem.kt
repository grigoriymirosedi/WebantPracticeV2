package com.example.webantpracticev2.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val user_id: Int?,
    val title: String?,
    val body: String?
    )
