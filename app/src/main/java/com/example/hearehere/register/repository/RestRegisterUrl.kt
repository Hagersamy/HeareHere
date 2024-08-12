package com.example.hearehere.register.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestRegisterUrl {
    private const val baseUrl =""
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiServiceRegisterInterface by lazy {
        retrofit.create(ApiServiceRegisterInterface::class.java)
    }
}