package com.getloc.githublite.data

import androidx.lifecycle.LiveData
import com.getloc.githublite.data.local.entity.UserEntity
import com.getloc.githublite.data.local.room.GithubDao
import com.getloc.githublite.data.remote.response.User

class FavoriteRepository (private val githubDao: GithubDao) {

    val readAll: LiveData<List<UserEntity>> = githubDao.getFav()

    suspend fun addFav(userEntity: UserEntity) {
        githubDao.addFav(userEntity)
    }

    suspend fun updateFav(userEntity: UserEntity) {
        githubDao.updateFav(userEntity)
    }

    suspend fun deleteFav(userEntity: UserEntity) {
        githubDao.deleteFav(userEntity)
    }

    suspend fun deleteAll() {
        githubDao.deleteAll()
    }

    suspend fun checkById(username: String) = githubDao.checkById(username)
}