package com.example.userlist.di

import com.example.userlist.repository.UserRepository
import com.example.userlist.repository.UserRepsitoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by BMHenry at 9/8/2021
 */
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideUserRepository(repository: UserRepsitoryImp): UserRepository
}