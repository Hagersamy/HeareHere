package com.example.hearehere.home.Repository

import com.example.hearehere.Model.HomeUserData
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