package com.siheung.siheung_security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProgressBarViewModel: ViewModel() {
    private var progress = MutableLiveData<Int>()

    val height: LiveData<Int>
        get() = progress

    init {
        progress.value = 170
    }

    fun increase() {
        progress.value = progress.value?.plus(1)
    }
}