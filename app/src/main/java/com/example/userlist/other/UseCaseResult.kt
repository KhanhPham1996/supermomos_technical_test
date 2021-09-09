package com.example.userlist.other

sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val errorMessage: String = "",val errorCode : Int = 0) : UseCaseResult<Nothing>()
}