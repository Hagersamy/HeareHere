package com.example.hearehere.Login.Repository

import com.example.hearehere.Model.LoginData

class LoginDataRepository {
    private val apiService = RestLoginUrl.api

    suspend fun loginUser(email: String, password: String): LoginData {
        val request = LoginRequest(email, password)
        return apiService.loginUser(request)
    }
}