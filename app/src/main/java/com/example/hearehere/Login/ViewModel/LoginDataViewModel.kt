package com.example.hearehere.Login.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearehere.Login.Repository.LoginDataRepository
import com.example.hearehere.Model.LoginData
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