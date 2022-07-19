package com.mahmoudashraf.geideaapp.domain.repository

import com.mahmoudashraf.geideaapp.data.entity.UserDetails
import com.mahmoudashraf.geideaapp.data.entity.UserResponse

interface UsersRepository {
    suspend fun getAllUsers(): UserResponse
    suspend fun getUserByID(userID: Int): UserDetails
}