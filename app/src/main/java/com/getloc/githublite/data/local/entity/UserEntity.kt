package com.getloc.githublite.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_entities")
data class UserEntity (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var username: String,
        var avatar: String
) : Parcelable