package com.example.hearehere.home.repo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestURL {
    private const val baseUrl =""
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiServiceHomeInterface by lazy {
        retrofit.create(ApiServiceHomeInterface::class.java)
    }

}