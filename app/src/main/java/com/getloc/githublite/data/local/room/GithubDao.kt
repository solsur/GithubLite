package com.getloc.githublite.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.getloc.githublite.data.local.entity.UserEntity

@Dao
interface GithubDao {

    @Query("SELECT * FROM user_entities")
    fun getAllUser(): LiveData<List<UserEntity>>

    @Insert
    suspend fun addtoFavorite(userEntity: UserEntity)

    @Query("SELECT * FROM user_entities where favorite = 1")
    fun getFavoriteUser(): LiveData<List<UserEntity>>

    @Query("SELECT count(*) FROM  user_entities WHERE user_entities.id = :id")
    fun getCheckUserId(id: Int): Int


    @Query("DELETE FROM user_entities WHERE user_entities.id = :id")
    suspend fun removeFavorite(id: Int): Int

    @Transaction
    @Query("SELECT * FROM user_entities WHERE id = :id")
    fun getDetailUserById(id: String): LiveData<UserEntity>

}