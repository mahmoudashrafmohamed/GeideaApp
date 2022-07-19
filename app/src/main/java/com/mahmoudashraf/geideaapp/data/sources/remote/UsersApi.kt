package com.mahmoudashraf.geideaapp.data.sources.remote

import com.mahmoudashraf.geideaapp.data.entity.UserDetails
import com.mahmoudashraf.geideaapp.data.entity.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApi {
    @GET("users")
    suspend fun getAllUsers(@Query("per_page") pageSize: Int = 20): UserResponse

    @GET("users/{id}")
    suspend fun getUserByID(@Path("id") userID: Int): UserDetails
}