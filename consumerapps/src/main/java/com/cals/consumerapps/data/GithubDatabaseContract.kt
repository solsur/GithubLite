package com.cals.consumerapps.data

import android.net.Uri
import android.provider.BaseColumns

object GithubDatabaseContract {
    const val AUTHORITY = "com.getloc.githublite"
    const val SCHEME = "content"

    internal class GithubDatabaseColums: BaseColumns {
        companion object{
            private const val TABLE_NAME = "database_user"
            const val ID = "id"
            const val USERNAME = "login"
            const val AVATAR_URL = "avatar_url"

            val CONTENT_URI = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()!!
        }
    }
}