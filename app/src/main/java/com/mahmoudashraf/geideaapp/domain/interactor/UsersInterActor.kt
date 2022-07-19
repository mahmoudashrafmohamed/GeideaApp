package com.mahmoudashraf.geideaapp.domain.interactor

import com.mahmoudashraf.geideaapp.data.entity.UserDetails
import com.mahmoudashraf.geideaapp.data.entity.UserResponse
import com.mahmoudashraf.geideaapp.domain.repository.UsersRepository
import javax.inject.Inject

class UsersInterActor @Inject constructor(private val usersRepository: UsersRepository) {
    suspend fun getAllUsers(): UserResponse = usersRepository.getAllUsers()
    suspend fun getUserByID(userID: Int): UserDetails = usersRepository.getUserByID(userID)
}