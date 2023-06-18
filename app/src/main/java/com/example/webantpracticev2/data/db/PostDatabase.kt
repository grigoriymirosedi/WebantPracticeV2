package com.example.webantpracticev2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PostListItemLocal::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PostDatabase: RoomDatabase() {

    abstract fun getPostDao(): PostListItemDao

    companion object {
        @Volatile
        private var instance: PostDatabase? = null

        operator fun invoke() { }
    }

    private fun createDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            PostDatabase::class.java,
        "post_db.db").build()
}