package com.getloc.githublite.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.getloc.githublite.data.local.room.GithubDao
import com.getloc.githublite.data.local.room.GithubDatabase
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class UserContentProvider : ContentProvider() {

    private lateinit var githubDao: GithubDao

    override fun onCreate(): Boolean {
        githubDao = context?.let {
            GithubDatabase.getInstance(it)?.githubDao()
        }!!
        return false
    }

    override fun query(
            uri: Uri, projection: Array<String>?, selection: String?,
            selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor: Cursor?
        when(uriMatcher.match(uri)){
            ID_DATABASE_USER_DATA ->{
                cursor= githubDao.getAllUser()
                if (context!=null){
                    cursor.setNotificationUri(context?.contentResolver, uri)
                }
            }else ->{
            cursor = null
        }
        }
        return cursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
            uri: Uri, values: ContentValues?, selection: String?,
            selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    companion object{
        private const val AUTHORITY = "com.getloc.githublite"
        private const val TABLE_NAME = "database_user"
        const val ID_DATABASE_USER_DATA = 1
        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, TABLE_NAME, ID_DATABASE_USER_DATA)
        }
    }

}