package com.tae.remembersearchapplication.api.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity constructor(
    val login: String = "",
    @PrimaryKey val id: Int = 0,
    val avatar_url: String = "",
    val isChecked: Boolean = false
)