package com.tae.remembersearchapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.viewmodel.BaseViewModel
import com.tae.remembersearchapplication.api.data.UserRes
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.ResponseBody

interface UserVM {
    fun getUserInfo(name: String)
}

class UserVMImpl(private val repo: UserRepo) : BaseViewModel(), UserVM {

    private val _user = MutableLiveData<UserRes>()
    val userTask: LiveData<UserRes> = _user

    override fun getUserInfo(name: String) {
        repo.getUser(name)
            .onEach {
                when (it) {
                    is ApiResult.Success -> _user.value = it.data
                    is ApiResult.Error -> _user.value = it.error
                }
            }
            .launchIn(viewModelScope)
    }
}