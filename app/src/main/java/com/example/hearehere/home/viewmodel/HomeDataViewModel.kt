package com.example.hearehere.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearehere.home.repo.HomeDataRepository


import com.example.hearehere.models.HomeUserData

import kotlinx.coroutines.launch

class HomeDataViewModel:ViewModel() {
    private val repository: HomeDataRepository by lazy {
        HomeDataRepository()
    }
    private val _homeData = MutableLiveData<List<HomeUserData>>()
    val homeData : LiveData<List<HomeUserData>> get()= _homeData

    private val _error =MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _booksPagingData = MutableLiveData<List<HomeUserData.Data.Data.Book>>()
    val booksPagingData: LiveData<List<HomeUserData.Data.Data.Book>> get() = _booksPagingData


//    val booksPagingData: Flow<PagingData<HomeUserData.Data.Data.Book>> = Pager(
//        config = PagingConfig( pageSize = 10, enablePlaceholders = false),
//        pagingSourceFactory = { HomePagingSource { page, size ->
//                repository.fetchBooks(page, size)
//            }
//        }
//    ).flow
//        .cachedIn(viewModelScope)

    fun fetchHomeData(){
        viewModelScope.launch{
            try {
                val homeUsers = repository.fetchData() // search about it
                _homeData.postValue(homeUsers)
                  } catch (e: Exception) {
                _error.postValue(e.message)
            }

        }
    }
    fun fetchBookPagingData(page: Int, size: Int) {
        viewModelScope.launch {
            try {
                val books = repository.fetchBookPaging(page, size)
                _booksPagingData.postValue(books)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

}