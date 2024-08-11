package com.example.hearehere.Register.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class RegisterDataViewModelFactory (private val repository: RegisterDataViewModelFactory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterDataViewModel() as T
    }
}