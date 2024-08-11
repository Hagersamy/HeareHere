package com.example.hearehere.home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeDataViewModelFactory (private val repository: HomeDataViewModelFactory): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeDataViewModel() as T
    }
}