package com.getloc.githublite.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.getloc.githublite.data.local.entity.UserEntity
import com.getloc.githublite.data.local.room.GithubDao
import com.getloc.githublite.data.local.room.GithubDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application){

    private var githubDao : GithubDao? = null
    private var userDB : GithubDatabase? = GithubDatabase.getInstance(application)

    init {
        githubDao = userDB?.githubDao()
    }

    fun getFavoriteUser(): LiveData<List<UserEntity>>?  {
        return githubDao?.getFavoriteUser()
    }
}