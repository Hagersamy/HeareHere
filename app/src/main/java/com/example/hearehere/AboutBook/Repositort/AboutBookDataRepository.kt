package com.example.hearehere.AboutBook.Repositort

import com.example.hearehere.models.AboutBookData


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