package com.example.hearehere.AboutBook.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearehere.AboutBook.Repositort.AboutBookDataRepository
import com.example.hearehere.models.AboutBookData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AbouBookDataViewModel(): ViewModel() {
    private val repository: AboutBookDataRepository by lazy {
        AboutBookDataRepository()
    }
//    val booksPaged: LiveData<PagingData<AboutBookData.Data.Category>> =
//        repository.getBooksPaged().cachedIn(viewModelScope)

    private val _bookData = MutableLiveData<AboutBookData>()
    val bookData: LiveData<AboutBookData> get() = _bookData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchBookById(bookId: Int){
        viewModelScope.launch {
            try {
                val book = withContext(Dispatchers.IO) {
                    repository.getBookById(bookId)
                }
                _bookData.postValue(book)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

}