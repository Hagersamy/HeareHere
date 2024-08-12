package com.example.hearehere.login.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearehere.login.repository.LoginDataRepository
import com.example.hearehere.models.LoginData
import kotlinx.coroutines.launch

class LoginDataViewModel:ViewModel() {
    private val repository:LoginDataRepository by lazy {
        LoginDataRepository()
    }

    private val _loginData = MutableLiveData<LoginData>()
    val loginData: LiveData<LoginData> get() = _loginData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.loginUser( email, password)
                _loginData.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}