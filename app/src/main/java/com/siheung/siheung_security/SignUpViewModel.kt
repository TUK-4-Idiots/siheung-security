package com.siheung.siheung_security

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private var _progress = MutableLiveData(0)
    private var _nameInput = MutableLiveData("")
    private var _emailInput = MutableLiveData("")
    private var _passwdInput = MutableLiveData("")
    private var _verifyPasswdInput = MutableLiveData("")

    val dbHelper = DBHelper(getApplication<Application>().applicationContext, "siheung-security", null, 1)
    var database = dbHelper.writableDatabase

    private val progress : LiveData<Int>
        get() = _progress

    private val nameInput : LiveData<String>
        get() = _nameInput

    private val emailInput : LiveData<String>
        get() = _emailInput

    private val passwdInput : LiveData<String>
        get() = _passwdInput

    private val verifyInput : LiveData<String>
        get() = _verifyPasswdInput

    fun nextStep() {
            _progress.value = _progress.value?.plus(33)
    }

    fun updateName(newName: String) {
        _nameInput.value = newName
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
    fun getName(): LiveData<String> {
        return nameInput
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

    fun signUpUser() {
        dbHelper.insert(database, Random.Default.nextInt(), nameInput.value!!, emailInput.value!!, passwdInput.value!!)
    }
}