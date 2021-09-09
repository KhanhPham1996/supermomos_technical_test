package com.example.userlist.localdata

import androidx.room.*
import com.example.userlist.model.User
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(model: List<User>)

    @Query("SELECT * FROM tb_user_entity")
    suspend fun getAll(): List<User>



}