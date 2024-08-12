package com.example.hearehere.login.repository

import com.example.hearehere.models.LoginData

class LoginDataRepository {
    private val apiService = RestLoginUrl.api

    suspend fun loginUser(email: String, password: String): LoginData {
        val request = LoginRequest(email, password)
        return apiService.loginUser(request)
    }
}