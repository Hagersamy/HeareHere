package com.example.hearehere.Register.Repository

import com.example.hearehere.Model.RegisterData
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