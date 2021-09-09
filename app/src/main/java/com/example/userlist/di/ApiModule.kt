package com.example.userlist.di

import com.example.userlist.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by BMHenry at 9/8/2021
 */
@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideInitializeApiService( retrofit: Retrofit)
            : UserService = retrofit.create(UserService::class.java)

}