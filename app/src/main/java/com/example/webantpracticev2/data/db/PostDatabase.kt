package com.example.webantpracticev2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.webantpracticev2.data.remote.dto.PostListItem

@Database(
    entities = [PostListItem::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PostDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostListItemDao

    companion object {
        @Volatile
        private var instance: PostDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PostDatabase::class.java,
                "post_database").build()
    }


}