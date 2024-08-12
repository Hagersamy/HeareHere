package com.example.hearehere.home.repository

import com.example.hearehere.models.HomeUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeDataRepository {
    private val apiService = RestURL.api
    suspend fun fetchData(): List<HomeUserData> {
        return withContext(Dispatchers.IO) {
            apiService.getAllHomeData()
        }
    }
    suspend fun fetchBookPaging(page: Int, size: Int): List<HomeUserData.Data.Data.Book> {
        return withContext(Dispatchers.IO) {
            apiService.getPagedBooks(page, size)
        }
    }
}