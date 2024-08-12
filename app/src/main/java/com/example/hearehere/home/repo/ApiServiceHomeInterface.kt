package com.example.hearehere.home.repo

import com.example.hearehere.models.HomeUserData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceHomeInterface {
    @GET("api/bookCollection")
    suspend fun getAllHomeData(): List<HomeUserData>

    @GET("api/bookCollection?page")
    suspend fun getPagedBooks(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<HomeUserData.Data.Data.Book>
}