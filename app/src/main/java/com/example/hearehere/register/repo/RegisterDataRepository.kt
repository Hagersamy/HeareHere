package com.example.hearehere.register.repo

import com.example.hearehere.models.RegisterData


class RegisterDataRepository {
    private val apiService = RestRegisterUrl.api

    suspend fun registerUser(name: String, email: String, password: String): RegisterData {
        val request = RegisterRequest(name, email, password)
        return apiService.registerUser(request)
    }

}