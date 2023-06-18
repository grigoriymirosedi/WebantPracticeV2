package com.example.webantpracticev2.data.db

import androidx.room.TypeConverter
import com.example.webantpracticev2.data.remote.dto.PostListItem

class Converters {

    @TypeConverter
    fun fromPostListItem(postListItem: PostListItem): String = postListItem.id.toString() + "," +
            postListItem.user_id.toString() + "," +
            postListItem.title + "," +
            postListItem.body

    @TypeConverter
    fun toPostListItem(postListItem: String): PostListItem {
        val result = postListItem.split(",")
        return PostListItem(
            id = result.get(0).toInt(),
            user_id = result.get(1).toInt(),
            title = result.get(2),
            body = result.get(3)
        )
    }

    @TypeConverter
    fun fromData(data: List<PostListItem>): String {
        var result: String = ""
        for(postListItem in data) {
            result += postListItem.id.toString() + "," +
                    postListItem.user_id.toString() + "," +
                    postListItem.title + "," +
                    postListItem.title + "|"
        }
        return result
    }

    @TypeConverter
    fun toData(data: String): List<PostListItem> {
        val result_list = mutableListOf<PostListItem>()
        val string_result = data.split("|")
        for (string in string_result) {
            var result = string.split(",")
            result_list.add(
                PostListItem(
                    id = result.get(0).toInt(),
                    user_id = result.get(1).toInt(),
                    title = result.get(2),
                    body = result.get(3)
                )
            )
        }
        return result_list
    }
}

