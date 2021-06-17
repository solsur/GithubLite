package com.getloc.githublite.data.local.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.getloc.githublite.data.local.entity.UserEntity

@Dao
interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFav(userEntity: UserEntity)

    @Query("SELECT * FROM user_entities ORDER BY id ASC")
    fun getFav(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user_entities ORDER BY id ASC")
    fun getAllFav(): Cursor

    @Update
    suspend fun updateFav(userEntity: UserEntity)

    @Delete
    suspend fun deleteFav(userEntity: UserEntity)

    @Query("DELETE FROM user_entities")
    suspend fun deleteAll()

    @Query("SELECT * FROM user_entities WHERE user_entities.username = :username")
    suspend fun checkById(username: String): UserEntity


}