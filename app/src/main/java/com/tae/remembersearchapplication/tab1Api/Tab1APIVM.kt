package com.tae.remembersearchapplication.tab1Api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.util.Log
import com.tae.baselibrary.viewmodel.BaseViewModel
import com.tae.remembersearchapplication.RememberApp
import com.tae.remembersearchapplication.tab1Api.api.data.User
import com.tae.remembersearchapplication.tab1Api.api.data.UserRes
import com.tae.remembersearchapplication.tab1Api.ui.OnCheckChangeListener
import com.tae.remembersearchapplication.tab2DB.db.UserEntity
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.FieldPosition

interface Tab1VM {
    fun getUserInfo(name: String)
}

class Tab1VMImpl(private val repo: Tab1APIRepo) : BaseViewModel(), Tab1VM, OnCheckChangeListener {

    private val _user = MutableLiveData<UserRes>()
    val userTask: LiveData<UserRes> = _user

    private val _alert = MutableLiveData<Unit>()
    val alertTask: LiveData<Unit> = _alert

    val onCheckChangeListener: OnCheckChangeListener = this

    var userName = ""

    override fun getUserInfo(name: String) {
        userName = name
        if (name.isEmpty()) {
            _alert.value = Unit
            return
        }
        combine(repo.getUserFromApi(name), repo.getAllUser()) { api, db ->
            when (api) {
                is ApiResult.Success -> {
                    api.data.items?.forEach { user ->
                        user.isChecked =
                            db?.firstOrNull { f -> f.id == user.id }?.isChecked ?: false
                    }
                    _user.value = api.data
                }
                is ApiResult.Error -> _user.value = api.error
                null -> _user.value = UserRes()
            }
        }.launchIn(viewModelScope)
    }


    override fun onCheckChange(user: User) {
        viewModelScope.launch {
            //insert user
            if (user.isChecked)
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
            else repo.deleteUser(user.id)
            RememberApp.INSTANCE.needRefresh = true
        }
    }


}