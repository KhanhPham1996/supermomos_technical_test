package com.example.userlist.repository

import com.example.userlist.model.User
import com.example.userlist.model.UserDetail
import com.example.userlist.other.UseCaseResult

/**
 * Created by BMHenry at 9/8/2021
 */
interface UserRepository {

    suspend fun fetchDataAndInsertDB() : UseCaseResult<Boolean>
    suspend fun getUserFromDB() : UseCaseResult<List<User>>
    suspend fun getUserDetail(userLogin: String) : UseCaseResult<UserDetail>
}