package com.getloc.githublite.data.remote.api

import com.getloc.githublite.data.remote.response.Detail
import com.getloc.githublite.data.remote.response.User
import com.getloc.githublite.data.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {

    @GET("/search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("/users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
            @Path("username") username: String
    ): Call<Detail>

    @GET("/users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowersUser(
            @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("/users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowingUser(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}