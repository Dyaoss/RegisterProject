package com.dhproject.registerproject.data

import androidx.room.Insert
import androidx.room.Query

interface UserDao {

    @Insert
    suspend fun registerUser(user: User): Long

    @Query("SELECT * FROM users WHERE userId = :userId AND password = :password")
    suspend fun loginUser(userId: String, password: String): User

}