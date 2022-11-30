package com.tae.remembersearchapplication.ui

import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.api.serverResult
import com.tae.baselibrary.repository.BaseRepository
import com.tae.remembersearchapplication.api.data.UserRes
import com.tae.remembersearchapplication.api.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

class UserRepo(val api: UserService) : BaseRepository() {

    fun getUser(name:String):Flow<ApiResult<UserRes>> = flow{
        emit(serverResult( api.search(name)))
    }.flowOn(Dispatchers.IO)
}