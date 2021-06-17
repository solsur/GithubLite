package com.getloc.githublite.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.getloc.githublite.data.FavoriteRepository
import com.getloc.githublite.data.local.entity.UserEntity
import com.getloc.githublite.data.local.room.GithubDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application){

    private val favoriteRepository: FavoriteRepository
    val readAllData: LiveData<List<UserEntity>>
    val favoriteUser= MutableLiveData<UserEntity>()

    init {
        val githubDAO = GithubDatabase.getInstance(application).githubDao()
        favoriteRepository = FavoriteRepository(githubDAO)
        readAllData = favoriteRepository.readAll
    }

    fun addFavorite(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.addFav(user)
        }
    }

    fun updateFavorite(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.updateFav(user)
        }
    }

    fun deleteFavorite(user: UserEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteFav(user)
        }

    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.deleteAll()
        }
    }

    fun checkById(username:String){
        viewModelScope.launch (Dispatchers.IO) {
            favoriteUser.postValue(favoriteRepository.checkById(username))
        }
    }
}