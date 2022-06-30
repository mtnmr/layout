package com.example.swipesample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel: ViewModel() {

    private var _count:MutableLiveData<Int> = MutableLiveData(0)
    val count:LiveData<Int> = _count

    fun countUp(){
        _count.value = _count.value!! + 1
    }

    fun countDown(){
        _count.value = _count.value!! - 1
    }
}