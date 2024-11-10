package com.dhproject.registerproject.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun registerUser(
        userId: String,
        nickName: String,
        password: String,
        passwordCheck: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val user =
                User(
                    userId = userId,
                    nickName = nickName,
                    password = password,
                    passwordCheck = password
                )
            val userId = userRepository.registerUser(user)
            onResult(userId > 0)
        }
    }

    fun loginUser(userId: String, password: String, callback: (Boolean, User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.loginUser(userId, password)
            if (user != null) {
                callback(true, user)
            } else {
                callback(false, null)
            }
        }
    }
    fun checkUserIdExists(userId: String, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isExist = userRepository.isUserIdExist(userId)
            callback(isExist)
        }
    }
}

class RegisterViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}