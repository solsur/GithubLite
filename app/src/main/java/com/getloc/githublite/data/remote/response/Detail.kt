package com.getloc.githublite.data.remote.response

data class Detail(
    val avatar_url: String,
    val followers: Int,
    val followers_url: String,
    val following: Int,
    val following_url: String,
    val id: Int,
    val location: String,
    val login: String,
    val name: String
)