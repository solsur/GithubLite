package com.getloc.githublite.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getloc.githublite.data.remote.api.RetrofitClient
import com.getloc.githublite.data.remote.response.User
import com.getloc.githublite.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel  : ViewModel() {

    val listSearchUser = MutableLiveData<ArrayList<User>>()

    fun getSearchUser(): LiveData<ArrayList<User>> = listSearchUser

    fun setSearchUser(query: String){
        RetrofitClient.instanceAPI
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        listSearchUser.postValue(response.body()?.items)
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("Gagal : ", t.message.toString())
                }
            })
    }
}