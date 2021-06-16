package com.getloc.githublite.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.getloc.githublite.data.local.entity.UserEntity
import com.getloc.githublite.data.local.room.GithubDao
import com.getloc.githublite.data.local.room.GithubDatabase
import com.getloc.githublite.data.remote.api.RetrofitClient
import com.getloc.githublite.data.remote.response.Detail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    val user = MutableLiveData<Detail>()
    private var githubDao : GithubDao? = null
    private var userDB : GithubDatabase? = GithubDatabase.getInstance(application)

    init {
        githubDao = userDB?.githubDao()
    }

    fun setUserDetail(username: String){
        RetrofitClient.instanceAPI
                .getDetailUser(username)
                .enqueue(object : Callback<Detail> {
                    override fun onResponse(call: Call<Detail>, response: Response<Detail>) {
                        if (response.isSuccessful) {
                            user.postValue(response.body())
                        }
                    }
                    override fun onFailure(call: Call<Detail>, t: Throwable) {
                        Log.d("onFailure: ", t.message.toString())
                    }

                })
    }

    fun getUserDetail() : LiveData<Detail> {
        return user
    }

    fun addFavorite(id: Int,login: String, avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            val user = UserEntity(
                id,
                login,
                avatarUrl
            )
            githubDao?.addtoFavorite(user)
        }
    }

    suspend fun checkUserId(id: Int) = githubDao?.getCheckUserId(id)

    fun removeFavorite(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            githubDao?.removeFavorite(id)
        }
    }



}