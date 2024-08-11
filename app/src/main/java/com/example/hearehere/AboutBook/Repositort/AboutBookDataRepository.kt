package com.example.hearehere.AboutBook.Repositort

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.hearehere.AboutBook.Paging.BookPagingSource
import com.example.hearehere.Model.AboutBookData


class AboutBookDataRepository {
    private val apiServer = RestAboutBookUrl.api

    suspend fun getBookById(bookId: Int): AboutBookData {
        return apiServer.getBookById(bookId)
    }

//    fun getBooksPaged() = Pager(
//        config = PagingConfig(
//            pageSize = 10,
//            enablePlaceholders = false
//        ),
//        pagingSourceFactory = {
//            BookPagingSource { page, size -> apiServer.getBooks(page, size) }
//        }
//    ).liveData
}