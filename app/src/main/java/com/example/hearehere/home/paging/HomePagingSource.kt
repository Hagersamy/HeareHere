package com.example.hearehere.home.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.hearehere.models.HomeUserData

class HomePagingSource (private val fetchBooks: suspend (page: Int, size: Int) -> List<HomeUserData.Data.Data.Book>):
  PagingSource<Int, HomeUserData.Data.Data.Book>() {

    override fun getRefreshKey(state: PagingState<Int, HomeUserData.Data.Data.Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeUserData.Data.Data.Book> {
        return try {
            val page =
                params.key ?: 1 // params contain information about the requested load operation --
            val books = fetchBooks(page, params.loadSize)

            LoadResult.Page(
                data = books,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (books.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}