package com.example.hearehere.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearehere.models.RegisterData
import com.example.hearehere.register.repo.RegisterDataRepository
import kotlinx.coroutines.launch

class RegisterDataViewModel():ViewModel() {

    private val repository: RegisterDataRepository by lazy {
        RegisterDataRepository()
    }

    private val _registerData = MutableLiveData<RegisterData>()
    val registerData: LiveData<RegisterData> get() = _registerData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.registerUser(name, email, password)
                _registerData.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}