package com.example.hearehere.AboutBook.Repositort

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestAboutBookUrl {
    private const val baseUrl =""
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: ApiServiceAboutBookInterface by lazy {
        retrofit.create(ApiServiceAboutBookInterface::class.java)
    }

}