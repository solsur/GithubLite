package com.getloc.githublite.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_entities")
data class UserEntity (
        @PrimaryKey
        val id: Int,
        val login: String,
        val avatar_url: String
) :Serializable