package com.dhproject.registerproject

import android.app.Application
import com.dhproject.registerproject.data.AppDatabase
import com.dhproject.registerproject.data.UserRepository

class MyApp : Application() {
    private val database by lazy { AppDatabase.getDatabase(this) }
    val userRepository: UserRepository by lazy { UserRepository(database.userDao())}

}