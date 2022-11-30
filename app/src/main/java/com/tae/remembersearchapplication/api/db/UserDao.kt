package com.tae.remembersearchapplication.api.db

import androidx.room.*
import java.time.ZoneId

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(item: List<UserEntity>)

    @Query("select * from user")
    suspend fun allUsers(): List<UserEntity>

    @Query("SELECT * FROM user " +
                   "WHERE login LIKE :filter || '%' ")
    fun usersWithFilter(filter: String): List<UserEntity>?

    @Query("delete from user where id = :id")
    suspend fun deleteUser(id: String)
}