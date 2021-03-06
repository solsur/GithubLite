package com.getloc.githublite.ui.detail.tab.followers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getloc.githublite.data.remote.api.RetrofitClient
import com.getloc.githublite.data.remote.response.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<User>>()

    fun getFollowers(): LiveData<ArrayList<User>> = listFollowers
    fun setFollowers(username: String){
        RetrofitClient.instanceAPI
            .getFollowersUser(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(call: Call<ArrayList<User>>, response: Response<ArrayList<User>>) {
                    if (response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }
            })
    }
}