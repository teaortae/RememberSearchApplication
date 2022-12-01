package com.tae.remembersearchapplication.api.db

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(item: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item: UserEntity)

    @Query("select * from user")
    suspend fun allUsers(): List<UserEntity>

    @Query("SELECT * FROM user " +
                   "WHERE login LIKE :filter || '%' ")
    fun usersWithFilter(filter: String): List<UserEntity>?

    @Query("delete from user where id = :id")
    suspend fun deleteUser(id: String)
}