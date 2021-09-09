package com.example.userlist.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.userlist.model.User


@Database(
    entities = [
        User::class,

    ],
    version = 1,
    exportSchema = false
)


abstract class UserDatabase : RoomDatabase() {
    abstract fun searchDao(): UserDao

}