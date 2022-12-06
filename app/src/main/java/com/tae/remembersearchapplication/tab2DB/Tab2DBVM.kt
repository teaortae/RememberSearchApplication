package com.tae.remembersearchapplication.tab2DB

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tae.baselibrary.viewmodel.BaseViewModel
import com.tae.remembersearchapplication.RememberApp
import com.tae.remembersearchapplication.tab1Api.api.data.User
import com.tae.remembersearchapplication.tab2DB.db.UserEntity
import com.tae.remembersearchapplication.tab2DB.ui.OnCheckChangeListener
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

    private val _alert = MutableLiveData<Unit>()
    val alertTask: LiveData<Unit> = _alert

    val onCheckChangeListener: OnCheckChangeListener = this
    var userName = "" //for refresh

    override fun getUserInfo(name: String) {
        userName = name
        if (name.isEmpty()) {
            _alert.value = Unit
            return
        }
        repo.searchUser(name)
            .onEach {
                val userList: ArrayList<UserEntity> = arrayListOf()
                userList.addAll(it ?: listOf())
                _user.value = userList.getHeaderLetter()
            }
            .launchIn(viewModelScope)
    }

    override fun onCheckChange(user: User) {
        viewModelScope.launch {
            repo.deleteUser(user.id)
            getUserInfo(userName)
            RememberApp.INSTANCE.needRefresh = true
        }
    }


    private fun ArrayList<UserEntity>.getHeaderLetter(): List<UserEntity> {
        val mSectionList: ArrayList<UserEntity> = arrayListOf()
        sortWith { user1, user2 ->
            user1.login[0].uppercase()
                .compareTo(
                    user2.login[0].uppercase()
                )
        }

        var lastHeader: String? = ""
        forEach {
            val header: String = it.login[0].uppercase()
            if (lastHeader != header) {
                lastHeader = header
                mSectionList.add(UserEntity(header = header, isHeader = true))
            }
            mSectionList.add(it)
        }

        return mSectionList
    }
}