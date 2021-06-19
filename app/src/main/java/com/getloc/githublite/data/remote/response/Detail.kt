package com.getloc.githublite.data.remote.response

data class Detail(
    val login: String,
    val id: Int,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val avatar_url: String,
    val following_url: String,
    val location: String,
    val name: String
)