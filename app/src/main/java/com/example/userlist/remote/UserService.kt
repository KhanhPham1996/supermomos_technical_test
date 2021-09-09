package com.example.userlist.remote

import com.example.userlist.model.User
import com.example.userlist.model.UserDetail
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Deferred
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by BMHenry at 9/8/2021
 */

interface UserService {
    @GET("users")
    fun fetchUserListAsync() : Deferred<List<User>>

    @GET("users/{user_id}")
    fun fetchUserDetailAsync(
        @Path(value = "user_id") id: String) :Deferred<UserDetail>

}