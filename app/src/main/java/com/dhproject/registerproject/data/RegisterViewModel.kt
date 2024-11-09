package com.dhproject.registerproject.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun registerUser(
        userId: String,
        password: String,
        nickname: String,
        email: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val user =
                User(userId = userId, password = password, nickname = nickname, email = email)
            val userId = userRepository.registerUser(user)
            onResult(userId > 0)
        }
    }

    fun loginUser(userId: String, password: String, onResult: (Boolean, User?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.loginUser(userId, password)
            onResult(user != null, user)
        }
    }
}