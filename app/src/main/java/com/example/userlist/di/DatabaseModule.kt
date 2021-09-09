package com.example.userlist.di

import android.content.Context
import androidx.room.Room
import com.example.userlist.localdata.UserDao
import com.example.userlist.localdata.UserDatabase
import com.example.userlist.other.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): UserDatabase {
        return Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideSearchDao(appEngineerDatabase: UserDatabase): UserDao {
        return appEngineerDatabase.searchDao()
    }


}

