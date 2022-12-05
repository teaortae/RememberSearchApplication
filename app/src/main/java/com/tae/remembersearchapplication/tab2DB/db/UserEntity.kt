package com.tae.remembersearchapplication.tab2DB.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity constructor(
    val login: String = "",
    @PrimaryKey val id: Int = 0,
    val avatar_url: String = "",
    var isChecked: Boolean = false,
    val isHeader: Boolean = false,
    val header: String = ""
)

