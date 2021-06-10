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

    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUser(query: String){
        RetrofitClient.instanceAPI
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    if (response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }

            })

    }


    fun getSearchUser(): LiveData<ArrayList<User>> {
        return  listUser
    }

}