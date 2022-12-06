package com.tae.remembersearchapplication.tab1Api

import com.tae.baselibrary.api.ApiResult
import com.tae.baselibrary.api.serverResult
import com.tae.baselibrary.repository.BaseRepository
import com.tae.remembersearchapplication.ext.loading
import com.tae.remembersearchapplication.tab1Api.api.data.UserRes
import com.tae.remembersearchapplication.tab1Api.api.service.UserService
import com.tae.remembersearchapplication.tab2DB.db.AppDatabase
import com.tae.remembersearchapplication.tab2DB.db.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Tab1APIRepo(private val api: UserService, private val db: AppDatabase) : BaseRepository() {

    fun getUserFromApi(name: String): Flow<ApiResult<UserRes>?> = flow {
        emit(serverResult(api.search(name)))
    }.flowOn(Dispatchers.IO).loading()

    suspend fun insertUser(userEntity: UserEntity) {
        db.userDao().insertUser(userEntity)
    }

    suspend fun deleteUser(id: Int) {
        db.userDao().deleteUser(id)
    }

    fun getAllUser(): Flow<List<UserEntity>?> = flow {
        emit(db.userDao().allUsers())
    }.flowOn(Dispatchers.IO).loading()


}