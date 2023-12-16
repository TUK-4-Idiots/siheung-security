package com.siheung.siheung_security

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {
    private var _progress = MutableLiveData(0)
    private var _nameInputLength = MutableLiveData(0)
    private var _emailInput = MutableLiveData("")
    private var _passwdInput = MutableLiveData("")
    private var _verifyPasswdInput = MutableLiveData("")

    private val progress : LiveData<Int>
        get() = _progress

    private val nameInputLength : LiveData<Int>
        get() = _nameInputLength

    private val emailInput : LiveData<String>
        get() = _emailInput

    private val passwdInput : LiveData<String>
        get() = _passwdInput

    private val verifyInput : LiveData<String>
        get() = _verifyPasswdInput

    fun nextStep() {
            _progress.value = _progress.value?.plus(33)
    }

    fun updateNameLength(newLength: Int) {
        _nameInputLength.value = newLength
    }

    fun updateEmail(newEmail: String) {
        _emailInput.value = newEmail
    }

    fun updatePasswd(newPasswd: String) {
        _passwdInput.value = newPasswd
    }

    fun updateVerifyPasswd(newVerifyPasswd: String) {
        _verifyPasswdInput.value = newVerifyPasswd
    }

    @JvmName("callProgress")
    fun getProgress(): LiveData<Int> {
        return progress
    }

    @JvmName("callNameLength")
    fun getNameLength(): LiveData<Int> {
        return nameInputLength
    }

    @JvmName("callEmail")
    fun getEmail(): LiveData<String> {
        return emailInput
    }

    @JvmName("callPasswd")
    fun getPasswd(): LiveData<String> {
        return passwdInput
    }

    @JvmName("callVerifyPasswd")
    fun getVerifyPasswd(): LiveData<String> {
        return verifyInput
    }
}