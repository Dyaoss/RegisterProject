package com.dhproject.registerproject.presentation.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel(){
    private val _userNickName = MutableLiveData<String>()
    val userNickName: LiveData<String> = _userNickName

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String> = _userId

    private val _userPassword = MutableLiveData<String>()
    val userPassword: LiveData<String> = _userPassword
}