package com.getloc.githublite.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.getloc.githublite.data.local.entity.UserEntity
import com.getloc.githublite.data.local.room.GithubDao
import com.getloc.githublite.data.local.room.GithubDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var githubDao: GithubDao?
    private var githubDatabase : GithubDatabase?

    init {
        githubDatabase = GithubDatabase.getInstance(application)
        githubDao = githubDatabase?.githubDao()
    }

    fun getFavoriteUser(): LiveData<List<UserEntity>>?  {
        return githubDao?.getFavoriteUser()
    }
}