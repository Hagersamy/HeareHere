package com.example.hearehere.AboutBook.Paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.engine.Resource
import com.example.hearehere.Model.AboutBookData

class BookPagingSource(private val fetchBooks: suspend (page: Int, size: Int) -> List<AboutBookData.Data.Category>):
    PagingSource<Int, AboutBookData.Data.Category>() {

        //i do not have any refresh - -
    override fun getRefreshKey(state: PagingState<Int, AboutBookData.Data.Category>): Int? {
        return state.anchorPosition?.let { anchorPosition -> //state contain information about current state --
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AboutBookData.Data.Category> {
        return try {
            val page = params.key ?: 1 // params contain information about the requested load operation --
            // Fetch data from the source (could be a network call, database query, etc.)
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