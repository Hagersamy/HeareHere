package com.example.hearehere.login.repository

import com.example.hearehere.models.LoginData
import retrofit2.http.Body
import retrofit2.http.POST


data class LoginRequest(
    val email: String,
    val password: String
)

interface ApiServiceLoginInterface {
    @POST("/api/logIn")
    suspend fun loginUser(@Body request: LoginRequest): LoginData

}
