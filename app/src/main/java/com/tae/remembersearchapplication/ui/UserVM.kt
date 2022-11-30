package com.tae.remembersearchapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tae.baselibrary.viewmodel.BaseViewModel
import okhttp3.ResponseBody

interface UserVM{
    fun getUserInfo()
}

class UserVMImpl(val repo:UserRepo) : BaseViewModel(),UserVM{

    private val _user = MutableLiveData<ResponseBody>()
    val userTask: LiveData<ResponseBody> = _user

    override fun getUserInfo() {
        repo.
    }
}