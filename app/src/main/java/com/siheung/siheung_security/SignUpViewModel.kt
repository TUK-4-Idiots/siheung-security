package com.siheung.siheung_security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private var _progress = MutableLiveData<Int>(0)
    private var _inputLength = MutableLiveData<Int>(0)

    private val progress : LiveData<Int>
        get() = _progress

    private val inputLength : LiveData<Int>
        get() = _inputLength

    fun nextStep() {
        _progress.value = _progress.value?.plus(30)
    }

    fun updateLength(newLength: Int) {
        _inputLength.value = newLength
    }

    @JvmName("callProgress")
    fun getProgress(): LiveData<Int> {
        return progress
    }

    @JvmName("callLength")
    fun getLength(): LiveData<Int> {
        return inputLength
    }
}