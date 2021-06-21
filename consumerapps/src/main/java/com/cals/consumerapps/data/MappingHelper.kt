package com.cals.consumerapps.data

import android.database.Cursor
import com.cals.consumerapps.response.User

object MappingHelper {

    fun mapCursor(cursor: Cursor?): ArrayList<User>{
        val list = ArrayList<User>()

        if (cursor!=null){
            while (cursor.moveToNext()){
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(GithubDatabaseContract.GithubDatabaseColums.ID))
                val username = cursor.getString(cursor.getColumnIndexOrThrow(GithubDatabaseContract.GithubDatabaseColums.USERNAME))
                val avatar = cursor.getString(cursor.getColumnIndexOrThrow(GithubDatabaseContract.GithubDatabaseColums.AVATAR_URL ))

                list.add(
                    User(
                        id,
                        username,
                        avatar
                    )
                )
            }
        }
        return list
    }
}