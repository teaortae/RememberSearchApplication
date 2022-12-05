package com.tae.remembersearchapplication.tab2DB

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tae.baselibrary.viewmodel.BaseViewModel
import com.tae.remembersearchapplication.tab1Api.api.data.User
import com.tae.remembersearchapplication.tab1Api.ui.OnCheckChangeListener
import com.tae.remembersearchapplication.tab2DB.db.UserEntity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

interface Tab2DBVM {
    fun getUserInfo(name: String)
}

class Tab2DBVMImpl(private val repo: Tab2DBRepo) : BaseViewModel(), Tab2DBVM,
    OnCheckChangeListener {

    private val _user = MutableLiveData<List<UserEntity>>()
    val userTask: LiveData<List<UserEntity>> = _user

    val onCheckChangeListener: OnCheckChangeListener = this

    override fun getUserInfo(name: String) {
        repo.searchUser(name)
            .onEach { _user.value = it }
            .launchIn(viewModelScope)
    }

    override fun onCheckChange(user: User) {
        viewModelScope.launch {
            repo.deleteUser(user.id)
        }
    }

}