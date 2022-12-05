package com.tae.remembersearchapplication.tab1Api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.viewmodel.BaseViewModel
import com.tae.remembersearchapplication.tab1Api.api.data.User
import com.tae.remembersearchapplication.tab1Api.api.data.UserRes
import com.tae.remembersearchapplication.tab1Api.ui.OnCheckChangeListener
import com.tae.remembersearchapplication.tab2DB.db.UserEntity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.FieldPosition

interface Tab1VM {
    fun getUserInfo(name: String)
    fun getUserInfoFromDB()
}

class Tab1VMImpl(private val repo: Tab1APIRepo) : BaseViewModel(), Tab1VM, OnCheckChangeListener {

    private val _user = MutableLiveData<UserRes>()
    val userTask: LiveData<UserRes> = _user

    private val _onCheckChange = MutableLiveData<User>()
    val onCheckChangeTask: LiveData<User> = _onCheckChange

    private val _userFromDb = MutableLiveData<List<UserEntity>>()
    val userFromDbTask: LiveData<List<UserEntity>> = _userFromDb

    val onCheckChangeListener: OnCheckChangeListener = this

    override fun getUserInfo(name: String) {
        repo.getUserFromApi(name)
            .onEach {
                when (it) {
                    is ApiResult.Success -> _user.value = it.data
                    is ApiResult.Error -> _user.value = it.error
                    null -> _user.value = UserRes()
                }
            }
            .launchIn(viewModelScope)
    }

    override fun getUserInfoFromDB() {
        repo.getAllUser()
            .onEach { _userFromDb.value = it }
            .launchIn(viewModelScope)
    }

    override fun onCheckChange(user: User) {
        viewModelScope.launch {
            //insert user
            repo.insertUser(
                UserEntity(
                    user.login,
                    user.id,
                    user.avatar_url,
                    user.isChecked,
                    user.isHeader,
                    user.header
                )
            )
        }
    }
}