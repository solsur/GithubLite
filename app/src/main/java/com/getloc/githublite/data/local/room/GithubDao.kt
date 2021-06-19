package com.getloc.githublite.data.local.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.getloc.githublite.data.local.entity.UserEntity

@Dao
interface GithubDao {
    @Insert
    suspend fun addFavorite(userEntity: UserEntity)

    @Query("SELECT count(*) FROM user_entities WHERE user_entities.id = :id")
    suspend fun getCheckUserId(id: Int): Int

    @Query("DELETE FROM user_entities WHERE user_entities.id = :id")
    suspend fun removeFavorite(id: Int): Int

    @Query("SELECT * FROM user_entities")
    fun getAllUser(): Cursor

    @Query("SELECT * FROM user_entities")
    fun getFavoriteUser(): LiveData<List<UserEntity>>

}