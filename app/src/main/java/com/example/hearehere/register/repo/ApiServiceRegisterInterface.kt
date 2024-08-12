package com.example.hearehere.register.repo

import com.example.hearehere.models.RegisterData
import retrofit2.http.Body
import retrofit2.http.POST


data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
interface ApiServiceRegisterInterface {
    @POST("/api/register")
    suspend fun registerUser(@Body request: RegisterRequest): RegisterData ///ask about it
}