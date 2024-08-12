package com.example.hearehere.login.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginDataViewModelFactory (private val repository: LoginDataViewModelFactory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginDataViewModel() as T
    }
}