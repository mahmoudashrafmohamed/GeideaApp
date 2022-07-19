package com.mahmoudashraf.geideaapp.data.repository

import com.mahmoudashraf.geideaapp.data.entity.UserDetails
import com.mahmoudashraf.geideaapp.data.entity.UserResponse
import com.mahmoudashraf.geideaapp.data.sources.local.UserDatabase
import com.mahmoudashraf.geideaapp.data.sources.remote.UsersApi
import com.mahmoudashraf.geideaapp.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: UsersApi,
    private val localDataSource: UserDatabase
) : UsersRepository {
    override suspend fun getAllUsers(): UserResponse {
       val users = remoteDataSource.getAllUsers()
        localDataSource.userDao().insertAll(*users.data.toTypedArray())
        return users
    }

    override suspend fun getUserByID(userID: Int): UserDetails {
        return remoteDataSource.getUserByID(userID)
    }
}