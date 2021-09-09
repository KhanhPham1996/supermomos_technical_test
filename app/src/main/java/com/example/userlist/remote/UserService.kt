package com.example.userlist.remote

import com.example.userlist.model.User
import com.example.userlist.model.UserDetail
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path


interface UserService {
    @GET("users")
    fun fetchUserListAsync() : Deferred<List<User>>

    @GET("users/{user_id}")
    fun fetchUserDetailAsync(
        @Path(value = "user_id") id: String) :Deferred<UserDetail>

}