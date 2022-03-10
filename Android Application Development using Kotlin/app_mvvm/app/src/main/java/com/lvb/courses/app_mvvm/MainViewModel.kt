package com.lvb.courses.app_mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mRepo = PersonRepository()
    private var mTxtTemplate = MutableLiveData<String>()
    var textWelcome = mTxtTemplate

    private var mLogin = MutableLiveData<Boolean>()
    var login = mLogin

    fun login(name : String) {
        mLogin.value = mRepo.login(name)
    }

}