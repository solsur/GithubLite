package com.getloc.githublite.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_entities")
data class UserEntity (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Int,
        @ColumnInfo(name = "login")
        var login: String,
        @ColumnInfo(name = "avatar_url")
        var avatar_url: String,
        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false
): Parcelable