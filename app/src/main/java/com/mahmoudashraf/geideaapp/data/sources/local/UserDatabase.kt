package com.mahmoudashraf.geideaapp.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahmoudashraf.geideaapp.data.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}