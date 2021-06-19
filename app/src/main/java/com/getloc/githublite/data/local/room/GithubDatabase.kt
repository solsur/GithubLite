package com.getloc.githublite.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.getloc.githublite.data.local.entity.UserEntity
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [UserEntity::class], version = 1)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun githubDao(): GithubDao

    companion object {
        private var INSTANCE: GithubDatabase? = null

        @InternalCoroutinesApi
        fun getInstance(context: Context): GithubDatabase?{
            if (INSTANCE==null){
                synchronized(GithubDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        GithubDatabase::class.java,
                        "database_user"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}