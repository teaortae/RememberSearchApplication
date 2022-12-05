package com.tae.remembersearchapplication.tab2DB

import com.tae.baselibrary.repository.BaseRepository
import com.tae.remembersearchapplication.ext.loading
import com.tae.remembersearchapplication.tab2DB.db.AppDatabase
import com.tae.remembersearchapplication.tab2DB.db.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Tab2DBRepo(private val db: AppDatabase) : BaseRepository() {

    fun getAllUser(): Flow<List<UserEntity>?> = flow {
        emit(db.userDao().allUsers())
    }.flowOn(Dispatchers.IO).loading()

    fun searchUser(name: String): Flow<List<UserEntity>?> = flow {
        emit(db.userDao().usersWithFilter(name))
    }.flowOn(Dispatchers.IO).loading()

    suspend fun deleteUser(id: Int) {
        db.userDao().deleteUser(id)
    }

}