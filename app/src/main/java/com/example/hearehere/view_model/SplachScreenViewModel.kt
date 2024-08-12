package com.example.hearehere.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplachScreenViewModel: ViewModel() {
    private val _isReady= MutableLiveData<Boolean>(false)
    val isReady: LiveData<Boolean> get()= _isReady

    fun setReady(ready: Boolean){
        _isReady.value=ready
    }
}