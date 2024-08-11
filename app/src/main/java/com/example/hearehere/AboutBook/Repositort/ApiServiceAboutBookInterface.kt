package com.example.hearehere.AboutBook.Repositort

import com.example.hearehere.Model.AboutBookData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceAboutBookInterface {
    @GET("api/book/id")
    suspend fun getBookById(@Path("id") id: Int): AboutBookData
}