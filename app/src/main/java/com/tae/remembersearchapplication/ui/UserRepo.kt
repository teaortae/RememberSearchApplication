package com.tae.remembersearchapplication.ui

import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.api.serverResult
import com.tae.baselibrary.repository.BaseRepository
import com.tae.remembersearchapplication.api.data.UserRes
import com.tae.remembersearchapplication.api.db.AppDatabase
import com.tae.remembersearchapplication.api.db.UserEntity
import com.tae.remembersearchapplication.api.service.UserService
import com.tae.remembersearchapplication.ext.loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepo(private val api: UserService, private val db: AppDatabase) : BaseRepository() {

    fun getUser(name: String): Flow<ApiResult<UserRes>?> = flow {
        emit(serverResult(api.search(name)))
    }.flowOn(Dispatchers.IO).loading()

    suspend fun insertUser(userEntity: UserEntity) {
        db.userDao().insertUser(userEntity)
    }

    fun getAllUser(): Flow<List<UserEntity>?> = flow {
        emit(db.userDao().allUsers())
    }.flowOn(Dispatchers.IO).loading()
}

