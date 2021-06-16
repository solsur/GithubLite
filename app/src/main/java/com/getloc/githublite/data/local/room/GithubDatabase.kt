package com.getloc.githublite.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.getloc.githublite.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao

    companion object {
        @Volatile
        private var INSTANCE: GithubDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): GithubDatabase =
                INSTANCE ?: synchronized(this) {
                    Room.databaseBuilder(
                            context.applicationContext,
                            GithubDatabase::class.java,
                            "catalogue_database.db"
                    ).allowMainThreadQueries().build().apply { INSTANCE = this }
                }
    }
}