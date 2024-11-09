package com.dhproject.registerproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val userId: String,
    val password: String,
    val nickname: String,
    val email: String
)
