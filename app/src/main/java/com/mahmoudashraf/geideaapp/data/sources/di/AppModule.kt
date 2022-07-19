package com.mahmoudashraf.geideaapp.data.sources.di

import android.content.Context
import androidx.room.Room
import com.mahmoudashraf.geideaapp.core.roomDBName
import com.mahmoudashraf.geideaapp.data.sources.local.UserDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

class AppModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            roomDBName
        ).build()
    }
}