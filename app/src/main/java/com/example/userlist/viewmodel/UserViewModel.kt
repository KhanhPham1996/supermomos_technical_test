package com.example.userlist.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlist.model.User
import com.example.userlist.model.UserDetail
import com.example.userlist.other.UseCaseResult
import com.example.userlist.other.Util.isOnline
import com.example.userlist.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val userList = MutableLiveData<List<User>>()
    val userDetail = MutableLiveData<UserDetail>()

    val showLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    init {
        showLoading.value = true
    }

    fun getUserList(context: Context, showLoadingDialog: Boolean) {
        if (isOnline(context)) {
            showLoading.value = showLoadingDialog
            viewModelScope.launch {
                var result = withContext(Dispatchers.IO) {
                    repository.fetchDataAndInsertDB()
                }
                when (result) {
                    is UseCaseResult.Success -> {
                        getUserFromDB(false)
                    }
                    is UseCaseResult.Error -> {
                        errorMessage.value = result.errorMessage
                    }
                }
                showLoading.value = false

            }
        } else {
            getUserFromDB(true)
        }


    }

    private fun getUserFromDB(showLoadingDialog: Boolean) {
        showLoading.value = showLoadingDialog
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO) {
                repository.getUserFromDB()
            }
            when (result) {
                is UseCaseResult.Success -> {
                    userList.value = result.data
                }
                is UseCaseResult.Error -> {
                    errorMessage.value = result.errorMessage
                }
            }
            showLoading.value = false

        }
    }

    fun getUserDetail(context: Context,userLogin: String, isShowLoading: Boolean) {
        if (isOnline(context)) {
            showLoading.value = isShowLoading
            viewModelScope.launch {
                var result = withContext(Dispatchers.IO) {
                    repository.getUserDetail(userLogin)
                }
                when (result) {
                    is UseCaseResult.Success -> {
                        userDetail.value = result.data
                    }
                    is UseCaseResult.Error -> {
                        errorMessage.value = result.errorMessage
                    }
                }
                showLoading.value = false

            }
        }
        else    errorMessage.value = "Please check the internet connection"
    }
}