package com.mahmoudashraf.geideaapp.data.sources.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mahmoudashraf.geideaapp.core.BASE_URL
import com.mahmoudashraf.geideaapp.core.roomDBName
import com.mahmoudashraf.geideaapp.data.repository.UsersRepositoryImpl
import com.mahmoudashraf.geideaapp.data.sources.local.UserDatabase
import com.mahmoudashraf.geideaapp.data.sources.remote.UsersApi
import com.mahmoudashraf.geideaapp.domain.interactor.UsersInterActor
import com.mahmoudashraf.geideaapp.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            roomDBName
        ).build()
    }

    @Provides
    @Singleton
    fun provideUsersApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Provides
    @Singleton
     fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
     fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        remoteDataSource: UsersApi,
        localDataSource: UserDatabase
    ): UsersRepository {
        return UsersRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun provideUserInterActor(
       usersRepository: UsersRepository
    ): UsersInterActor {
        return UsersInterActor(usersRepository)
    }
}