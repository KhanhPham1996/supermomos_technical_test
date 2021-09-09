package com.example.userlist.repository

import com.example.userlist.localdata.UserDao
import com.example.userlist.model.User
import com.example.userlist.model.UserDetail
import com.example.userlist.other.UseCaseResult
import com.example.userlist.remote.UserService
import javax.inject.Inject

/**
 * Created by BMHenry at 9/8/2021
 */

class UserRepsitoryImp @Inject constructor(
    private val userService: UserService,
    private val dao: UserDao
) : UserRepository {
    override suspend fun fetchDataAndInsertDB(): UseCaseResult<Boolean> {
        return try {
            var userList = userService.fetchUserListAsync().await()
            dao.add(userList)
            UseCaseResult.Success(true)
        } catch (e: Exception) {
            UseCaseResult.Error(e.toString())
        }
    }

    override suspend fun getUserFromDB(): UseCaseResult<List<User>> {
        return try {
            var userListResult = dao.getAll()
            UseCaseResult.Success(userListResult)

        } catch (e: Exception) {
            UseCaseResult.Error(e.toString())
        }
    }

    override suspend fun getUserDetail(userLogin : String): UseCaseResult<UserDetail> {
        return try {
            var userDetail = userService.fetchUserDetailAsync(userLogin).await()
            UseCaseResult.Success(userDetail)
        } catch (e: Exception) {
            UseCaseResult.Error(e.toString())
        }
    }

}