package com.example.hearehere.AboutBook.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class AboutBookDataViewModelFactory (private val repository: AboutBookDataViewModelFactory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AbouBookDataViewModel() as T
    }

}