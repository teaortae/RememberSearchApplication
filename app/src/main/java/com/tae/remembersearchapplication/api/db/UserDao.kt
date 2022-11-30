package com.tae.remembersearchapplication.api.db

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(item: List<UserEntity>)

    @Query("select * from user")
    suspend fun allUsers(): List<UserEntity>

    @Query("SELECT * FROM user " +
                   "WHERE name LIKE :filter || '%' " +
                   "OR surname LIKE :filter || '%' " +
                   "OR email LIKE :filter || '%'")
    fun usersWithFilter(filter: String): List<UserEntity>?

    @Query("delete from user where email = :email")
    suspend fun deleteUser(email: String)
}