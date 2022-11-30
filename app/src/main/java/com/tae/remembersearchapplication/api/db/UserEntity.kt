package com.tae.remembersearchapplication.api.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity constructor(
    @PrimaryKey val email: String,
    val gender: String,
    val name: String,
    val surname: String,
    val street: String,
    val city: String,
    val state: String,
    val registered: String,
    val phone: String,
    val pictureLarge: String,
    val pictureMedium: String,
)