package com.example.hearehere.login.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestLoginUrl {
    private const val baseUrl =""
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiServiceLoginInterface by lazy {
        retrofit.create(ApiServiceLoginInterface::class.java)
    }

}