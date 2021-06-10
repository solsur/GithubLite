package com.getloc.githublite.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getloc.githublite.data.remote.api.RetrofitClient
import com.getloc.githublite.data.remote.response.Detail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    val user = MutableLiveData<Detail>()

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

}