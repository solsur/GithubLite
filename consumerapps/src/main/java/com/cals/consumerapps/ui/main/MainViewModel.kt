package com.cals.consumerapps.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cals.consumerapps.data.GithubDatabaseContract
import com.cals.consumerapps.data.MappingHelper
import com.cals.consumerapps.response.User

class MainViewModel (application: Application) : AndroidViewModel(application) {

    val listUser = MutableLiveData<ArrayList<User>>()

    fun getUser(): LiveData<ArrayList<User>> = listUser
    fun  setUser(context: Context){
        val cursor= context.contentResolver.query(
            GithubDatabaseContract.GithubDatabaseColums.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listConverted = MappingHelper.mapCursor(cursor)
        listUser.postValue(listConverted)
    }
}