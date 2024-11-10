package com.dhproject.registerproject.data

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User): Long {
        return userDao.registerUser(user)
    }

    suspend fun loginUser(userId: String, password: String): User {
        return userDao.loginUser(userId, password)
    }

    suspend fun getUserById(userId: String): User? {
        return userDao.getUserById(userId)

    }

}